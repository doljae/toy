package com.example.practice.nullsafety

var a: String = "abc"

var b: String? = "abc"

fun main() {
    var b: String? = "abc"
    b = null
    println(b)

    val l = if (b != null) b.length else -1

    val c: String? = "Kotlin"
    if (b != null && b.length > 0) {
        println("String of length ${b.length}")
    } else {
        println("Empty string")
    }
}

