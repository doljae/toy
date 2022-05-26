package com.example.adaptor;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.WebClientResponse;
import com.example.repository.CustomRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebClientAdaptor {

    private final WebClient webClient;
    private final CustomRepository customRepository;

    public void useWebClientSexy() {

        final ResponseEntity<WebClientResponse> response =
            webClient.get()
                     .uri("/test/unauthorized")
                     .header("header1", "value1")
                     .header(CONTENT_TYPE,
                             MediaType.APPLICATION_JSON.toString())
                     .retrieve()
                     .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                         clientResponse.createException()
                                       .publishOn(Schedulers.boundedElastic())
                                       .map(exception -> {
                                           log.error(exception.toString());
                                           customRepository.save();
                                           return exception;
                                       }).flatMap(Mono::error))
                     .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                         clientResponse.createException().flatMap(Mono::error))
                     .toEntity(WebClientResponse.class)
                     .block();

        log.info(Objects.requireNonNull(Objects.requireNonNull(response).getBody()).response());
    }
}
