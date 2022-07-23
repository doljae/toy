package com.example.kopring.domain.board.dao

import com.example.kopring.domain.board.entity.Board
import com.example.kopring.domain.board.entity.BoardDao
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BoardRepositoryDecorator(val boardRepository: BoardRepository) {

    fun findById(id: Long): BoardDao =
        Board.toDao(boardRepository.findByIdOrNull(id) ?: throw RuntimeException())
}