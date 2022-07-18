package com.example.kopring.domain.board.application

import com.example.kopring.domain.board.dao.BoardRepository
import com.example.kopring.domain.board.dto.BoardRequest
import com.example.kopring.domain.board.entity.Board
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BoardService(val boardRepository: BoardRepository) {

    fun getBoard(id: Long): Board? = boardRepository.findByIdOrNull(id)
    fun getAllBoards(): List<Board> = boardRepository.findAll()
    fun postBoard(boardRequest: BoardRequest) {
        val (title, author, content) = boardRequest
        val board = Board(title, author, content)
        boardRepository.save(board)
    }
}