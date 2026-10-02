package com.carepilot.healthcare.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PiiPhiRedactionServiceTest {
    @Test void redactsEmailAndPhone() {
        String result = new PiiPhiRedactionService().redact("email test@example.com phone +91 9876543210");
        assertFalse(result.contains("test@example.com"));
        assertFalse(result.contains("9876543210"));
    }
}
