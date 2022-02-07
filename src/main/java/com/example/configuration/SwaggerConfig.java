package com.example.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi catApiGroup() {
        return GroupedOpenApi.builder()
                             .group("cat-group")
                             .pathsToMatch("/cat/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi dogApiGroup() {
        return GroupedOpenApi.builder()
                             .group("dog-group")
                             .pathsToMatch("/dog/**")
                             .build();
    }

    private static final String TITLE = "TITLE";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String LICENSE = "LICENSE";
    private static final String LICENSE_URL = "LICENSE_URL";
    private static final String TERMS_OF_SERVICE_URL = "TERMS_OF_SERVICE_URL";
    private static final String VERSION = "VERSION";

    @Bean
    public static OpenApiCustomiser globalOpenApiCustomer() {
        return openApi ->
            openApi.info(new Info()
                             .title(TITLE)
                             .description(DESCRIPTION)
                             .license(new License().name(LICENSE).url(LICENSE_URL))
                             .termsOfService(TERMS_OF_SERVICE_URL)
                             .version(VERSION));

    }
}
