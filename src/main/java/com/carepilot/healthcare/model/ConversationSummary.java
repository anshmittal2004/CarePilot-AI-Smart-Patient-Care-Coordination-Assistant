package com.carepilot.healthcare.model;

import java.time.Instant;

public record ConversationSummary(String conversationId, int messageCount, Instant lastActivity) {}
