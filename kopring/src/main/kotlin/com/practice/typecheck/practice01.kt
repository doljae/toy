package com.practice.typecheck

class practice01 {

}

fun cast(obj: Any) {
    if (obj is String) {
        println(obj.length)
    }

    if (obj !is String) {
        println("Not a String")
    } else {
        println(obj.length)
    }
}

fun demo(x: Any) {
    if (x is String) {
        println(x.length)
    }

    if (x !is String) return
    println(x.length)
}

fun demo2(x: Any) {
    if (x !is String || x.length == 0) return
    if (x is String && x.length > 0) {
        println(x.length)
    }
}

fun demo3(x: Any) {
    when (x) {
        is Int -> print(x + 1)
        is String -> print(x.length + 1)
        is IntArray -> print(x.sum())
    }

}

fun main() {
//    val x: String = y as String
//    val x: String? = y as String?
}