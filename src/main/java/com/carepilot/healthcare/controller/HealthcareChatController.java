package com.carepilot.healthcare.controller;

import com.carepilot.healthcare.model.ChatRequest;
import com.carepilot.healthcare.model.GroundedResponse;
import com.carepilot.healthcare.service.HealthcareChatService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai/healthcare/chat")
public class HealthcareChatController {
    private final HealthcareChatService service;
    public HealthcareChatController(HealthcareChatService service) { this.service = service; }
    @PostMapping("/sync") public GroundedResponse sync(@RequestBody ChatRequest request) { return service.chat(request); }
    @PostMapping("/async") public GroundedResponse async(@RequestBody ChatRequest request) { return service.chat(request); }
    @GetMapping("/ping") public Map<String,String> ping() { return Map.of("status", "online", "service", "CarePilot AI"); }
}
