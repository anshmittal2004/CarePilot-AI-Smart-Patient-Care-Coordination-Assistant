package com.carepilot.healthcare.model;

import java.util.List;

public record GroundedResponse(
        String workflowId,
        String conversationId,
        String answer,
        String targetLanguage,
        boolean escalated,
        List<Citation> citations,
        long responseTimeMs) {}
