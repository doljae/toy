package com.example.redis;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassicRequestWrapper {

    @JsonTypeInfo(use = Id.CLASS)
    @JsonSubTypes({
        @JsonSubTypes.Type(RequestDto.class),
        @JsonSubTypes.Type(RequestRecord.class)
    })
    public Object wrapped;
}
