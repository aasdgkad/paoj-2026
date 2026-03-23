package com.pao.laboratory05.audit;

import java.time.LocalDateTime;

public record AuditEntry(String action, String target, String timestamp) {
    public AuditEntry(String action, String target) {
        this(action, target, LocalDateTime.now().toString());
    }
}
