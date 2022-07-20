package com.example.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        final var httpClient = HttpClient.create()
                                         .doOnConnected(connection -> connection.addHandlerFirst(
                                             new ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS)))
                                         .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                                                 .wiretap("reactor.netty.http.client.HttpClient", LogLevel.TRACE);

        httpClient.warmup().block();

        return WebClient.builder()
                        .clientConnector(new ReactorClientHttpConnector(httpClient))
                        .filter(LogFilter.logRequest())
                        .filter(LogFilter.logResponse())
                        .build();
    }
}
