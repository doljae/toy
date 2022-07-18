package com.example.kopring.domain.board.api

import com.example.kopring.domain.board.application.BoardService
import com.example.kopring.domain.board.dto.BoardRequest
import com.example.kopring.domain.board.entity.Board
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(val boardService: BoardService) {
    @GetMapping("/boards/{id}")
    fun getBoard(@PathVariable("id") id: Long): Board? =
        boardService.getBoard(id)

    @PostMapping("/boards")
    fun postBoard(@RequestBody boardRequest: BoardRequest): Unit {
        boardService.postBoard(boardRequest)
    }

    @GetMapping("/boards")
    fun getBoards(): List<Board> = boardService.getAllBoards()
}