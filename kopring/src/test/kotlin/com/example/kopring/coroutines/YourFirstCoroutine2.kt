package com.example.kopring.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() = runBlocking {
//    launch { doWorld() }
//    println("Hello")
//}
//
//suspend fun doWorld() {
//    delay(1000L)
//    println("World")
//}

//fun main() = runBlocking {
//    doWorld2()
//}
//
//suspend fun doWorld2() = coroutineScope {
//    launch {
//        delay(1000L)
//        println("World")
//    }
//    println("Hello")
//}

fun main()= runBlocking {
    doWorld()
    println("Done")
}

suspend fun doWorld() = coroutineScope {
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}
