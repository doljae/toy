package com.example.configuration;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.Map;
import java.util.Map.Entry;

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
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

    private static final String APPLICATION_JSON_VALUE =
        org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
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
                                                         operation.addParametersItem(new Parameter().$ref(
                                                                      "#/components/parameters/CatOnlyHeader1"))
                                                                  .addParametersItem(new Parameter().$ref(
                                                                      "#/components/parameters/CatOnlyHeader2")))
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

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                            .addParameters("CatOnlyHeader1",
                                           new Parameter()
                                               .in("header")
                                               .required(true)
                                               .name("Cat-Only-Header1")
                                               .description("Cat Only Header1 description")
                                               .schema(new StringSchema()._default("headerDefaultValue")))
                            .addParameters("CatOnlyHeader2",
                                           new HeaderParameter()
                                               .required(true)
                                               .name("Cat-Only-Header2")
                                               .description("Cat Only Header2 description")
                                               .schema(new StringSchema()._default("headerDefaultValue"))));
    }

    public static OpenApiCustomiser globalOpenApiCustomer() {
        return openApi ->
            openApi.info(new Info()
                             .title(TITLE)
                             .description(DESCRIPTION)
                             .license(new License().name(LICENSE).url(LICENSE_URL))
                             .termsOfService(TERMS_OF_SERVICE_URL)
                             .version(VERSION))
                   .getPaths()
                   .values()
                   .forEach(pathItem ->
                                pathItem.readOperations()
                                        .forEach(operation -> {
                                            final ApiResponses apiResponses = operation.getResponses();
                                            for (Entry<HttpStatus, ApiResponse> entry : getApiResponseMap().entrySet()) {
                                                apiResponses.addApiResponse(
                                                    Integer.toString(entry.getKey().value()),
                                                    entry.getValue());
                                            }
                                            operation.addParametersItem(new HeaderParameter()
                                                                            .required(true)
                                                                            .name("Global-Header1")
                                                                            .description(
                                                                                "Global Header1 description")
                                                                            .schema(new StringSchema()._default(
                                                                                "headerDefaultValue")));
                                        }));

    }

    private static Map<HttpStatus, ApiResponse> getApiResponseMap() {
        final ApiResponse global400ApiResponses
            = new ApiResponse().description(BAD_REQUEST.getReasonPhrase())
                               .content(new Content().addMediaType(APPLICATION_JSON_VALUE,
                                                                   new MediaType().addExamples("sample",
                                                                                               exampleOfResponse400)
                                                                                  .addExamples("sample2",
                                                                                               exampleOfResponse400V2)));

        final ApiResponse global500ApiResponses
            = new ApiResponse().description(INTERNAL_SERVER_ERROR.getReasonPhrase())
                               .content(new Content().addMediaType(APPLICATION_JSON_VALUE,
                                                                   new MediaType().addExamples("sample",
                                                                                               exampleOfResponse500)));

        return Map.of(BAD_REQUEST, global400ApiResponses,
                      INTERNAL_SERVER_ERROR, global500ApiResponses);
    }

    private static final Example exampleOfResponse400 =
        new Example().value(ResponseDto.builder().status(false)
                                       .code(BAD_REQUEST.value())
                                       .description(BAD_REQUEST.getReasonPhrase())
                                       .build());

    private static final Example exampleOfResponse400V2 =
        new Example().value(ResponseDto.builder().status(false)
                                       .code(1234)
                                       .description(BAD_REQUEST.getReasonPhrase() + " sample 2")
                                       .build());
    private static final Example exampleOfResponse500 =
        new Example().value(ResponseDto.builder().status(false)
                                       .code(INTERNAL_SERVER_ERROR.value())
                                       .description(INTERNAL_SERVER_ERROR.getReasonPhrase())
                                       .build());
}
