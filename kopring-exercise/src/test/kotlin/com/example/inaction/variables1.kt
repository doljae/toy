package com.example.inaction

import kotlin.random.Random

fun main() {
    var a: String = "initial"
    println(a)

    var b: Int = 1
    val c = 3

    val d: Int = if (someCondition()) 2 else 1
    println(d)
}

fun someCondition(): Boolean {
    return Random.nextBoolean()
}