package com.example.inaction


fun main() {
    for (i in 0..3)
        print(i)
    println()
    for (i in 0 until 3)
        print(i)
    println()
    for (i in 2..8 step 2)
        print(i)
    println()
    for (i in 3 downTo 0)
        print(i)
    println()

    val x = 2
    if (x in 1..5) {
        println("profit")
    }

    if (x !in 6..10) {
        println("not profit")
    }
}