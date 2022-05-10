package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.WebClientResponse;

@ExtendWith(MockitoExtension.class)
class WebClientServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private WebClient webClient;
    @Mock(answer = Answers.RETURNS_SELF)
    private WebClient.Builder webClientBuilder;
    @InjectMocks
    private WebClientService webClientService;

    @Test
    void test() {
        when(webClient.get()
                      .uri(anyString())
                      .retrieve()
                      .toEntity(WebClientResponse.class)
                      .block())
            .thenReturn(ResponseEntity.ok(new WebClientResponse(HttpStatus.OK,
                                                                HttpStatus.OK.getReasonPhrase())));

        final WebClientResponse response = webClientService.request();
        assertEquals(HttpStatus.OK, response.httpStatus());
    }

    @Test
    void test2() {
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()
                      .uri(anyString())
                      .retrieve()
                      .onStatus(any(), any())
                      .toEntity(WebClientResponse.class)
                      .block())
            .thenReturn(ResponseEntity.ok(new WebClientResponse(HttpStatus.OK,
                                                                HttpStatus.OK.getReasonPhrase())));

        final WebClientResponse response = webClientService.requestV2();
        assertEquals(HttpStatus.OK, response.httpStatus());
    }
}