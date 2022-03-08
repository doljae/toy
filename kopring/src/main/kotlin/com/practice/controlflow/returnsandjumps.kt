package com.practice.controlflow

fun main() {

//    for (i in 1..100) {
//        for (j in 1..100) {
//            println("$i $j")
//            if (j > 50) break
//        }
//    }
//
//    loop@ for (i in 1..100) {
//        for (j in 1..100) {
//            println("$i $j")
//            if (j > 50) break@loop
//        }
//    }
    foo()
    println()
    foo2()
}

fun foo() {
//    listOf(1, 2, 3, 4, 5).forEach {
//        if (it == 3) return
//        println(it)
//    }
//    println("this point is unreachable")

    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit
        println(it)
    }
    println("done with explicit label")

    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach
        println(it)
    }
    println("done with implicit label")

    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return
        println(value)
    })
    println("done with anonymous function")
}

fun foo2() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop
            println(it)
        }
    }
    println("done with nested loop")
}