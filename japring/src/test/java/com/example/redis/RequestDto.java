package com.example.redis;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private Long id;
    private String name;
    private OffsetDateTime createdAt;

//    @Builder
//    @JsonCreator
//    public RequestDto(@JsonProperty("id") Long id,
//                      @JsonProperty("name") String name,
//                      @JsonProperty("createdAt") OffsetDateTime createdAt) {
//        this.id = id;
//        this.name = name;
//        this.createdAt = createdAt;
//    }
}
