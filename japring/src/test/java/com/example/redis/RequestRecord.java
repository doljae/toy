package com.example.redis;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "itemType",
    visible = true)
public record RequestRecord(Long id,
                            String name,
                            OffsetDateTime createdAt) {
}
