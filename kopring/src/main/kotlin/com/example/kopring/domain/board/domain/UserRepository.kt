package com.example.kopring.domain.board.domain

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
@EnableR2dbcRepositories
@Repository
interface UsersRepository : ReactiveCrudRepository<User, Long> {

    @Query("SELECT u.* FROM users u WHERE u.email = :email")
    fun findByEmail(email: String): Flux<User>
}