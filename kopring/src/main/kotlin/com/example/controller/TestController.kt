package com.example.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    fun testApi(): String {
        return "it works!"
    }

    @GetMapping("/test2/{id}")
    fun testApi2(@PathVariable id: String): String {
        println("path variable id:$id")
        return "it works!"
    }

}