package com.example.kopring.test

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


fun main() {
    // 디폴트 설정의 max thread 갯수가 10만개보다 작기 때문에 실행되지 않음
    repeat(100_000) {
        thread {
            Thread.sleep(1000L)
            println(".")
        }
    }

    // 코루틴은 가능함
    runBlocking {
        repeat(100_000) {
            launch {
                delay(1000L)
                println(".")
            }
        }
    }
}