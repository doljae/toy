package com.example.kopring.test

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    fun testApi(): String = "hello world!"
}