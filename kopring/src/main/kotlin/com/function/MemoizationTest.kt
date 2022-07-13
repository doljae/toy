package com.function

import kotlin.reflect.KProperty
import kotlin.system.measureTimeMillis

class Memoize<T, R>(val func: (T) -> R) {
    private val cache = mutableMapOf<T, R>()
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = { n: T ->
        cache.getOrPut(n) { func(n) }
    }
}

val fib: (Int) -> Long by Memoize { n: Int ->
    when (n) {
        0, 1 -> 1L
        else -> fib(n - 1) + fib(n - 2)
    }
}

fun main() {
    println(measureTimeMillis { fib(40) })
    println(measureTimeMillis { fib(45) })
    println(measureTimeMillis { fib(500) })
}