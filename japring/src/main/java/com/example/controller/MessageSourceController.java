package com.example.controller;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageSourceController {

    private final ReloadableResourceBundleMessageSource messageSource;

    @GetMapping("/message/test")
    public String test() {
        return messageSource.getMessage("greeting", new String[] { "seokjae" }, Locale.getDefault());
    }

    // 역으로 이용해서 현재 적용되어있는 basepath -> xlt key 버
    @GetMapping("/message/test2/{version}")
    public String test2(@PathVariable String version) {
        messageSource.setBasename("classpath:/" + version + "/messages");
        return "version is changed";
    }
}
