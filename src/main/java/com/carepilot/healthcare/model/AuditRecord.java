package com.carepilot.healthcare.model;

import java.time.Instant;
import java.util.List;

public record AuditRecord(String workflowId, String conversationId, Actor actor, String redactedQuery,
                          boolean escalated, List<Citation> citations, Instant timestamp) {}
