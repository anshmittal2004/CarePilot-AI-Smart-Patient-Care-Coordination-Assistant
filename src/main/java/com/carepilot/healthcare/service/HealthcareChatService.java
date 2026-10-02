package com.carepilot.healthcare.service;

import com.carepilot.healthcare.model.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HealthcareChatService {
    private final KnowledgeBaseService kb;
    private final PiiPhiRedactionService redactor;
    private final ChatHistoryService history;
    private final AuditService audit;
    private final ObjectProvider<ChatClient.Builder> chatClientBuilder;

    public HealthcareChatService(KnowledgeBaseService kb, PiiPhiRedactionService redactor,
                                 ChatHistoryService history, AuditService audit,
                                 ObjectProvider<ChatClient.Builder> chatClientBuilder) {
        this.kb = kb; this.redactor = redactor; this.history = history; this.audit = audit; this.chatClientBuilder = chatClientBuilder;
    }

    public GroundedResponse chat(ChatRequest request) {
        long started = System.currentTimeMillis();
        String workflowId = UUID.randomUUID().toString();
        String conversationId = request.conversationId() == null || request.conversationId().isBlank()
                ? history.nextConversationId() : request.conversationId();
        String query = redactor.redact(request.message());
        Actor actor = request.actor() == null ? Actor.PATIENT : request.actor();
        boolean escalated = HealthCareSafetyAdvisor.requiresEscalation(query);
        var matches = kb.search(query, 5);
        List<Citation> citations = matches.stream().map(e -> new Citation(e.getKey().source(), e.getKey().section(), e.getValue())).toList();

        String answer = generate(query, actor, request.targetLanguage(), matches, escalated);
        history.saveMessage(conversationId, ChatHistoryService.ROLE_USER, query);
        history.saveMessage(conversationId, ChatHistoryService.ROLE_ASSISTANT, redactor.redact(answer));
        audit.record(new AuditRecord(workflowId, conversationId, actor, query, escalated, citations, Instant.now()));

        return new GroundedResponse(workflowId, conversationId, answer, request.targetLanguage(), escalated, citations,
                System.currentTimeMillis() - started);
    }

    private String generate(String query, Actor actor, String language, List<java.util.Map.Entry<KnowledgeBaseService.Chunk,Double>> matches, boolean escalated) {
        if (matches.isEmpty()) {
            return "No matching healthcare policy found. Please consult your healthcare coordination team or compliance officer.";
        }
        String context = matches.stream().map(e -> "[source: %s - %s, relevance %.2f]\n%s".formatted(e.getKey().source(), e.getKey().section(), e.getValue(), e.getKey().text())).collect(Collectors.joining("\n\n"));
        String languageInstruction = language == null || language.isBlank() || language.equalsIgnoreCase("en") ? "English" : language;
        try {
            ChatClient.Builder builder = chatClientBuilder.getIfAvailable();
            if (builder != null) {
                return builder.build().prompt()
                        .system("You are CarePilot AI, a safe healthcare navigation assistant. Answer ONLY from the supplied approved context. Do not diagnose, prescribe, or invent facts. Be concise and structured. Respond in " + languageInstruction + ".")
                        .user("Role: " + actor + "\nQuestion: " + query + "\n\nAPPROVED KB CONTEXT:\n" + context)
                        .call().content();
            }
        } catch (Exception ignored) { }
        String base = matches.get(0).getKey().text();
        String response = "### Grounded answer\n" + base + "\n\n### Sources\n" + context;
        if (escalated) response += "\n\n**Safety notice:** This is informational guidance only. For emergencies or clinical decisions, contact an appropriate licensed healthcare professional immediately.";
        return response;
    }
}
