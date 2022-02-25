package com.example.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.dto.ResponseDto;
import com.example.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity<ResponseDto> handleException(CustomException exception) {
        log.info("Exception was occured:{}", exception.getClass().getSimpleName());

        return ResponseEntity.internalServerError().build();
    }
}
