package com.example.redis;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestFinalDto {
    private final Long id;
    private final String name;
    private final OffsetDateTime createdAt;

    @Builder
    @JsonCreator
    private RequestFinalDto(@JsonProperty("id") Long id,
                            @JsonProperty("name") String name,
                            @JsonProperty("createdAt") OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }
}
