package com.example.handler;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.dto.ResponseDto;
import com.example.exception.CustomException;

import feign.RetryableException;
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

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ResponseDto> handleException2(RetryableException e) {
        log.error(e.request().requestTemplate().feignTarget().name(), e);
        log.error("exception occured {} {}", 1234, StringUtils.defaultString(e.getMessage()), e);
        return ResponseEntity.internalServerError().build();

        //        log.info("Exception was occured:{}", e.getClass().getSimpleName());
//        log.error(e.toString());
//        log.error(e.getMessage());
//        log.error(e.getCause().toString());
//        log.error(e.getLocalizedMessage());
//        log.error(e.getClass().getName());
//        log.error(Arrays.toString(e.getSuppressed()));
//        log.error(Arrays.toString(Arrays.stream(e.getStackTrace()).toArray()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException2(Exception e) {
//        log.info("Exception was occured:{}", e.getClass().getSimpleName());
//        log.error(e.toString());
//        log.error(e.getMessage());
//        log.error(e.getCause().toString());
//        log.error(e.getLocalizedMessage());
//        log.error(e.getClass().getName());
//        log.error(Arrays.toString(e.getSuppressed()));
//        log.error(Arrays.toString(Arrays.stream(e.getStackTrace()).toArray()));
        log.error("exception occured {} {}", 1234, StringUtils.defaultString(e.getMessage()), e);
        return ResponseEntity.internalServerError().build();
    }
}
