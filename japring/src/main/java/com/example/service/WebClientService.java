//package com.example.service;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import com.example.dto.WebClientResponse;
//
//import lombok.RequiredArgsConstructor;
//import reactor.core.publisher.Mono;
//
//@Service
//@RequiredArgsConstructor
//public class WebClientService {
//
//    private final WebClient webClient;
//    private final WebClient.Builder webClientBuilder;
//
//    public WebClientResponse request() {
//
//        final ResponseEntity<WebClientResponse> response =
//            webClient.get()
//                     .uri("http://localhost:1234")
//                     .header("a", "a")
//                     .retrieve()
//                     .toEntity(WebClientResponse.class)
//                     .block();
//
//        return response.getBody();
//    }
//
//    public WebClientResponse requestV2() {
//
//        final ResponseEntity<WebClientResponse> response =
//            webClientBuilder.build().get()
//                            .uri("http://localhost:1234")
//                            .retrieve()
//                            .onStatus(HttpStatus::is4xxClientError,
//                                      clientResponse -> Mono.justOrEmpty(new RuntimeException()))
//                            .toEntity(WebClientResponse.class)
//                            .block();
//
//        return response.getBody();
//    }
//}
