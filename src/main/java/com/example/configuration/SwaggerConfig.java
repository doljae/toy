package com.example.configuration;

import java.util.EnumMap;
import java.util.Map;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.example.dto.ResponseDto;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;

@Configuration
public class SwaggerConfig {

    private static final String TITLE = "TITLE";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String LICENSE = "LICENSE";
    private static final String LICENSE_URL = "LICENSE_URL";
    private static final String TERMS_OF_SERVICE_URL = "TERMS_OF_SERVICE_URL";
    private static final String VERSION = "VERSION";

    @Bean
    public GroupedOpenApi catApiGroup() {
        return GroupedOpenApi.builder()
                             .group("cat-group")
                             .pathsToMatch("/cat/**")
                             .addOpenApiCustomiser(globalOpenApiCustomer())
                             .addOperationCustomizer((operation, handlerMethod) ->
                                                         operation.addParametersItem(new HeaderParameter().$ref(
                                                                      "#/components/parameters/myGlobalHeader1"))
                                                                  .addParametersItem(new HeaderParameter().$ref(
                                                                      "#/components/parameters/myGlobalHeader2"))
                                                                  .addParametersItem(new HeaderParameter().$ref(
                                                                      "#/components/parameters/myGlobalHeader3"))
                                                                  .addParametersItem(new HeaderParameter().$ref(
                                                                      "#/components/parameters/myGlobalHeader4")))
                             .build();
    }

    @Bean
    public GroupedOpenApi dogApiGroup() {
        return GroupedOpenApi.builder()
                             .group("dog-group")
                             .pathsToMatch("/dog/**")
                             .addOpenApiCustomiser(globalOpenApiCustomer())
                             .build();
    }

    public static OpenApiCustomiser globalOpenApiCustomer() {
        return openApi ->
            openApi.info(new Info()
                             .title(TITLE)
                             .description(DESCRIPTION)
                             .license(new License().name(LICENSE).url(LICENSE_URL))
                             .termsOfService(TERMS_OF_SERVICE_URL)
                             .version(VERSION));

    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                            .addParameters("myGlobalHeader1",
                                           new HeaderParameter()
                                               .required(true)
                                               .name("My-Global-Header1")
                                               .description("My Global Header1")
                                               .schema(new StringSchema()._default("default1")))
                            .addParameters("myGlobalHeader2",
                                           new HeaderParameter()
                                               .required(true)
                                               .name("My-Global-Header2")
                                               .description("My Global Header2")
                                               .schema(new StringSchema()._default("default2"))));
    }

    private Parameter generateHeader(String name, String description, String defaultValue) {
        return new Parameter()
            .in("header")
            .required(true)
            .name(name)
            .description(description)
            .schema(new StringSchema()._default(defaultValue));
    }

    private Map<HttpStatus, Example> getExampleMap() {
        final Map<HttpStatus, Example> exampleMap = new EnumMap<HttpStatus, Example>(HttpStatus.class);
        exampleMap.put(HttpStatus.BAD_REQUEST, exampleOfResponse400);
        exampleMap.put(HttpStatus.INTERNAL_SERVER_ERROR, exampleOfResponse500);

        return exampleMap;
    }

    private final Example exampleOfResponse400 = new Example().value(ResponseDto.builder().build());
    private final Example exampleOfResponse500 = new Example().value(ResponseDto.builder().build());
}
