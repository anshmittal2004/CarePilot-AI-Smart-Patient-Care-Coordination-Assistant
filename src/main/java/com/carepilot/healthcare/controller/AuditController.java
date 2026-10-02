package com.carepilot.healthcare.controller;

import com.carepilot.healthcare.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit")
public class AuditController {
    private final AuditService audit;
    public AuditController(AuditService audit) { this.audit = audit; }
    @GetMapping("/{workflowId}") public ResponseEntity<?> get(@PathVariable String workflowId) { return audit.find(workflowId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); }
}
