package com.carepilot.healthcare.controller;

import com.carepilot.healthcare.service.AdminAuthService;
import com.carepilot.healthcare.service.KnowledgeBaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/kb")
public class KnowledgeBaseController {
    private final KnowledgeBaseService kb; private final AdminAuthService auth;
    public KnowledgeBaseController(KnowledgeBaseService kb, AdminAuthService auth) { this.kb=kb; this.auth=auth; }
    @GetMapping("/status") public Map<String,Object> status() { return Map.of("vectorStoreLoaded", kb.size()>0, "storedChunks", kb.size(), "embeddingDimensions", kb.dimensions()); }
    @PostMapping("/ingest") public ResponseEntity<?> ingest(@RequestHeader(value="X-Admin-Token",required=false) String token) { if(!auth.isValid(token)) return ResponseEntity.status(401).body(Map.of("error","Admin authentication required")); return ResponseEntity.ok(Map.of("chunksCreated",kb.ingest(),"ingestionTimeMs",0,"documentId","carepilot-kb")); }
    @PostMapping("/upload") public ResponseEntity<?> upload(@RequestHeader(value="X-Admin-Token",required=false) String token,@RequestParam("file") MultipartFile file) { try { if(!auth.isValid(token)) return ResponseEntity.status(401).body(Map.of("error","Admin authentication required")); if(file.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error","Empty file")); return ResponseEntity.ok(kb.upload(file)); } catch(Exception e){ return ResponseEntity.internalServerError().body(Map.of("error",e.getMessage())); } }
}
