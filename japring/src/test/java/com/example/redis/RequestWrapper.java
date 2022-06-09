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
//@JsonTypeInfo(use = Id.CLASS)
public class RequestWrapper {

//    @JsonTypeInfo(use = Id.CLASS)
//    @JsonSubTypes({
//        @JsonSubTypes.Type(RequestDto.class),
//        @JsonSubTypes.Type(RequestRecord.class)
//    })
    public Object wrapped;
}
