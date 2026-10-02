package com.carepilot.healthcare.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class PiiPhiRedactionService {
    private static final Pattern EMAIL = Pattern.compile("(?i)\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b");
    private static final Pattern PHONE = Pattern.compile("\\b(?:\\+?\\d[\\d ()-]{8,}\d)\\b");
    private static final Pattern CARD = Pattern.compile("\\b(?:\\d[ -]*?){13,19}\\b");

    public String redact(String text) {
        if (text == null) return "";
        return CARD.matcher(PHONE.matcher(EMAIL.matcher(text).replaceAll("[REDACTED_EMAIL]")).replaceAll("[REDACTED_PHONE]")).replaceAll("[REDACTED_ID]");
    }
}
