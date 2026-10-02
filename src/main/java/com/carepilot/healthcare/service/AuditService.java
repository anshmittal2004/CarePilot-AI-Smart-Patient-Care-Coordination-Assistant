package com.carepilot.healthcare.service;

import com.carepilot.healthcare.model.AuditRecord;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuditService {
    private final Map<String, AuditRecord> records = new ConcurrentHashMap<>();
    public void record(AuditRecord record) { records.put(record.workflowId(), record); }
    public Optional<AuditRecord> find(String id) { return Optional.ofNullable(records.get(id)); }
    public int size() { return records.size(); }
}
