package com.carepilot.healthcare.model;

import java.time.Instant;

public record ChatMessageRecord(String conversationId, String role, String content, Instant timestamp) {}
