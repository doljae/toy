package com.example.practice.exception

const val input = "5"

val a: Int? = try {
    input.toInt()
} catch (e: NumberFormatException) {
    null
}

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

fun getName(str: String?): String {
    return str ?: "Unknown"
}

val s = getName("apple") ?: fail("name required")