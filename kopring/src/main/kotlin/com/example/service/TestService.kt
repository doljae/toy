package com.example.service

import org.springframework.stereotype.Service

@Service
class TestService {

    fun getTestVo(): TestVo {
        return TestVo("name", 17)
    }

    data class TestVo(val name: String, val age: Int)
}
