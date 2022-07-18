package com.example.kopring.domain.board.api

import com.example.kopring.domain.board.application.BoardService
import com.example.kopring.domain.board.entity.Board
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(val boardService: BoardService) {

    @GetMapping("/board")
    fun getBoard(@RequestParam("id") id: Long): Board? = boardService.getBoard(id)

}