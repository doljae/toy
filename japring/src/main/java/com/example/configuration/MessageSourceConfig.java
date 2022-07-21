package com.example.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MessageSourceConfig {

    @Value("${xlt-version}")
    private String xltVersion;


    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        log.info("xlt-version={}", xltVersion);
        log.info("base-path={}", "classpath:/" + xltVersion + "/messages");
        final var messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:/" + xltVersion + "/messages");
        messageSource.setDefaultLocale(Locale.getDefault());
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3);

        return messageSource;
    }

}
