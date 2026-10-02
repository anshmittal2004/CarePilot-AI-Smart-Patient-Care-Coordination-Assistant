package com.carepilot.healthcare.model;

public record AdminLoginResponse(boolean authenticated, String token, String message) {}
