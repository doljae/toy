package com.example.configuration;

import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import io.reactivex.rxjava3.annotations.NonNull;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public final class LogFilter {

    private LogFilter() {}

    @NonNull
    public static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(LogFilter::writeRequest);
    }

    @NonNull
    public static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(LogFilter::writeResponse);
    }

    @NonNull
    private static Mono<ClientRequest> writeRequest(@NonNull ClientRequest clientRequest) {
        try {
            log.info("[REQUEST] URI : {}, METHOD : {}, HEADERS : {}",
                     clientRequest.url(),
                     clientRequest.method(),
                     clientRequest.headers());
        } catch (Exception e) {
            log.error("[REQUEST] failed", e);
        }
        return Mono.just(clientRequest);
    }

    @NonNull
    private static Mono<ClientResponse> writeResponse(@NonNull ClientResponse clientResponse) {
        try {
            log.info("[RESPONSE] STATUS CODE : {}, HEADERS : {}",
                     clientResponse.rawStatusCode(),
                     clientResponse.headers().asHttpHeaders());
        } catch (Exception e) {
            log.error("[RESPONSE] failed", e);
        }
        return Mono.just(clientResponse);
    }
}
