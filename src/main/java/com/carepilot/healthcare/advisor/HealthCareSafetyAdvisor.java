package com.carepilot.healthcare.advisor;

import java.util.regex.Pattern;

public final class HealthCareSafetyAdvisor {
    private HealthCareSafetyAdvisor() {}
    private static final Pattern EMERGENCY = Pattern.compile("(?i)\\b(emergency|urgent|chest pain|can't breathe|difficulty breathing|suicide|self harm|overdose|bleeding|stroke|heart attack|unconscious|911|ambulance)\\b");
    private static final Pattern SENSITIVE = Pattern.compile("(?i)\\b(discharge|post-operative|complication|worsening|severe|allergic reaction|dispute|complaint|grievance|diagnosis|prescription|dosage)\\b");
    public static boolean requiresEscalation(String text) { return text != null && (EMERGENCY.matcher(text).find() || SENSITIVE.matcher(text).find()); }
}
