package com.carepilot.healthcare.controller;

import com.carepilot.healthcare.model.AdminLoginRequest;
import com.carepilot.healthcare.model.AdminLoginResponse;
import com.carepilot.healthcare.service.AdminAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminAuthService auth;
    public AdminController(AdminAuthService auth) { this.auth = auth; }

    @PostMapping("/login") public AdminLoginResponse login(@RequestBody AdminLoginRequest request) {
        return auth.login(request.adminId(), request.password())
                .map(t -> new AdminLoginResponse(true, t, "Authenticated"))
                .orElse(new AdminLoginResponse(false, null, "Invalid credentials"));
    }
    @GetMapping("/session") public ResponseEntity<?> session(@RequestHeader(value="X-Admin-Token", required=false) String token) {
        return auth.isValid(token) ? ResponseEntity.ok(Map.of("authenticated", true)) : ResponseEntity.status(401).body(Map.of("authenticated", false));
    }
    @PostMapping("/logout") public Map<String,Boolean> logout(@RequestHeader(value="X-Admin-Token", required=false) String token) { auth.logout(token); return Map.of("loggedOut", true); }
}
