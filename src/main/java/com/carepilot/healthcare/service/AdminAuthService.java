package com.carepilot.healthcare.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AdminAuthService {
    @Value("${admin.auth.id:admin123}") private String adminId;
    @Value("${admin.auth.password:Password@123}") private String password;
    private final Set<String> activeTokens = ConcurrentHashMap.newKeySet();

    public Optional<String> login(String id, String candidate) {
        if (adminId.equals(id) && password.equals(candidate)) {
            String token = UUID.randomUUID().toString();
            activeTokens.add(token);
            return Optional.of(token);
        }
        return Optional.empty();
    }
    public boolean isValid(String token) { return token != null && activeTokens.contains(token); }
    public void logout(String token) { if (token != null) activeTokens.remove(token); }
}
