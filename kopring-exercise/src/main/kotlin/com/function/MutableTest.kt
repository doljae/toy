package com.example.whiteboard.function

// const val을 사용하면 불변성을 보장할 수 있으나 유연하지 않음
object MutableVal {
    var count = 0
    val myString = "mutable"
        get() {
            return "$field ${++count}"
        }
}

fun main() {
    println("first call ${MutableVal.myString}")
    println("second call ${MutableVal.myString}")
    println("third call ${MutableVal.myString}")

    // read, write
    val mutableListOf = mutableListOf(1, 2, 3, 4, 5)
    println(mutableListOf)
    mutableListOf.add(6)
    println(mutableListOf)

    // read only list
    val listOf = listOf(1, 2, 3, 4, 5)
    println(listOf)
}