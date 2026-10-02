package com.carepilot.healthcare.service;

import com.carepilot.healthcare.model.ChatMessageRecord;
import com.carepilot.healthcare.model.ConversationSummary;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ChatHistoryService {
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ASSISTANT = "ASSISTANT";
    private final Map<String, List<ChatMessageRecord>> history = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public String nextConversationId() { return String.format("chat-%02d", counter.incrementAndGet()); }

    public void saveMessage(String conversationId, String role, String content) {
        if (conversationId == null || content == null) return;
        history.computeIfAbsent(conversationId, k -> Collections.synchronizedList(new ArrayList<>()))
                .add(new ChatMessageRecord(conversationId, role, content, Instant.now()));
    }

    public List<ChatMessageRecord> getMessages(String id) {
        return List.copyOf(history.getOrDefault(id, List.of()));
    }

    public List<ConversationSummary> summaries() {
        return history.entrySet().stream().map(e -> {
            List<ChatMessageRecord> list = e.getValue();
            Instant last = list.isEmpty() ? Instant.now() : list.get(list.size()-1).timestamp();
            return new ConversationSummary(e.getKey(), list.size(), last);
        }).sorted(Comparator.comparing(ConversationSummary::lastActivity).reversed()).toList();
    }

    public int totalMessages() { return history.values().stream().mapToInt(List::size).sum(); }
}
