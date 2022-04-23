package com.example.controller

import com.example.service.TestService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(val testService: TestService) {

    @GetMapping("/test")
    fun testApi(): String {
        return "it works!"
    }

    @GetMapping("/test2/{id}")
    fun testApi2(@PathVariable id: String): String {
        println("path variable id:$id")
        return "it works!"
    }

    @GetMapping("/test3")
    fun testApi3(): ResponseEntity<TestService.TestVo> {
        val testVo = testService.getTestVo();
        return ResponseEntity.ok(testVo);
    }

}