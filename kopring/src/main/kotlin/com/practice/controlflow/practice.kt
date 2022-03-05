package com.practice.controlflow

import java.util.*

class practice {
}

fun main() {
    val a = 5
    val b = 10

    val max = if (a < b) b else a

    val max2 = if (a > b) {
        print("choose a")
        a
    } else {
        print("choose b")
        b
    }

    val x = Random().nextInt(5)
    when (x) {
        1 -> println("x==1")
        2 -> println("x==2")
        else -> {
            println("x is neither 1 nor 2")
        }
    }

}

enum class Bit {
    ZERO, ONE
}

fun numericValue(bit: Bit): Int {
    return when (bit) {
        Bit.ZERO -> 0
        Bit.ONE -> 1
    }
}