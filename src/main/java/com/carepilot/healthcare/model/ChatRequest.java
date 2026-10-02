package com.carepilot.healthcare.model;

public record ChatRequest(String message, Actor actor, String conversationId, String targetLanguage) {}
