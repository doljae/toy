package com.example.dto;

import org.springframework.http.HttpStatus;

public record WebClientResponse(HttpStatus httpStatus, String response) {
}
