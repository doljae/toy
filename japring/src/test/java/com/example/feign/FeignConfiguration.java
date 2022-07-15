package com.example.feign;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class FeignConfiguration {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleRuntimeException(Exception e) {

        log.error("Exception[{}] {}", e.getMessage(),
                  StringUtils.defaultString(e.getMessage()), e);
        log.error(e.toString());
        log.error(e.getMessage());
        log.error(e.getCause().toString());
        log.error(e.getLocalizedMessage());
        log.error(e.getClass().getName());
        log.error(Arrays.toString(e.getSuppressed()));
        log.error(Arrays.toString(Arrays.stream(e.getStackTrace()).toArray()));
        if (e instanceof RetryableException ee) {
            log.error("it is RetryableException");
            ee.request().headers().forEach((key, value) -> log.error(key + " / " + value));
        }else{
            log.error("it is not");
        }
    }
}
