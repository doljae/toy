package com.example.redis;

import java.time.OffsetDateTime;

public record RequestRecord(Long id,
                            String name,
                            OffsetDateTime createdAt) {
}
