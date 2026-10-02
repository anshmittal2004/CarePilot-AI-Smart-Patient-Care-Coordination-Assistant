package com.carepilot.healthcare.controller;

import com.carepilot.healthcare.service.ChatHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/history")
public class ChatHistoryController {
    private final ChatHistoryService history;
    public ChatHistoryController(ChatHistoryService history) { this.history = history; }
    @GetMapping("/conversations") public Object conversations() { return history.summaries(); }
    @GetMapping("/{conversationId}") public ResponseEntity<?> conversation(@PathVariable String conversationId) {
        var messages = history.getMessages(conversationId);
        return messages.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(messages);
    }
}
