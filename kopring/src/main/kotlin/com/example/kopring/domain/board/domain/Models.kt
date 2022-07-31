package com.example.kopring.domain.board.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    private val id: Long? = null,
    val name: String,
    val login: String,
    val email: String,
    val avatar: String? = null
)

// rest models
data class ErrorMessage(val message: String)

data class UserDTO(
    val name: String,
    val login: String,
    val email: String,
    val avatar: String? = null
)

fun UserDTO.toModel(withId: Long? = null) = User(withId, this.name, this.login, this.email, this.avatar)