package com.example.kopring.domain.board.dto

data class BoardRequest(
    val title: String,
    val author: String,
    val content: String?
)
