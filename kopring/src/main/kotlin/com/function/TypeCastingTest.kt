package com.function

open class KotlinA() {
    val a = 5
}

class KotlinB : KotlinA() {
    val b = 10
}

fun main() {
    val obj: KotlinA = KotlinB()
    val obj2: Any = Any()
    // obj가 KotlinB 타입이라는 것이 체크되면 obj를 바로 KotlinB 타입으로 캐스팅
    if (obj is KotlinB) {
        println(obj.b)
    }

    if (obj2 !is String) {
        println("Not String")
    } else {
        println(obj2.length)
    }

    val x = "apple"

    if (x !is String || x.isEmpty()) return

    val something = mutableListOf(1, 2)

    if (something is List<*>) {
        something.
    }
}

fun check(list: List<Any>) {
    if (list is List<*>) {
        list.forEach { println(it) }
    }
    if (list is ArrayList) {
        list.forEach { println(it) }
    }
}