package com.example.practice.controlflow


enum class Color {
    RED, GREEN, BLUE
}

fun main() {
    val color = Color.RED

    when (color) {
        Color.RED -> println("red")
        Color.GREEN -> println("green")
        Color.BLUE -> println("blue")
    }

    when (color) {
        Color.RED -> println("red")
        else -> println("not red")
    }

    val x = 5
    when (x) {
        in listOf(1, 2, 3, 4, 5) -> print("x is valid")
        in 1..10 -> print("x is in range")
        !in 10..20 -> print("x is outside the range")
        else -> println("none of the above")
    }

    for (i in 1..3) {
        println(i)
    }
    for (i in 6 downTo 0 step 2) {
        println(i)
    }
    for ((index, value) in listOf(1, 2, 3, 4, 5).withIndex()) {
        println("$index $value")
    }
    var y = 5
    while (y > 0) {
        y--
    }

    do {
        y--
    } while (y > 0)
}

fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}
