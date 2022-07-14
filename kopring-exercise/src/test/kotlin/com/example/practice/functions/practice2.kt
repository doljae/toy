package com.example.practice.functions

import kotlin.math.abs
import kotlin.math.cos

infix fun Int.shl(x: Int): Int = 0


const val eps = 1E-10
tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (abs(x - cos(x)) < eps) x else findFixPoint(cos(x))

fun main() {
    val result = 1.shl(2)
    println(result)
    findFixPoint(eps)
}