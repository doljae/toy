package com.example.kopring.domain.board.dao

import com.example.kopring.domain.board.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {
}