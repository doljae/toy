package com.example.kopring.curry

import org.junit.jupiter.api.Test

class CurryingTest {

    @Test
    fun testSimpleQueryFunction() {
        simpleQueryFunction { println("Hello World!") }
    }

    private fun simpleQueryFunction(function: () -> Unit) {
        function.invoke()
    }

    fun aspect(function: () -> Int): Int {
        println("beforeCall")
        return function.invoke()
    }

    fun <T> aspectWithGeneric(function: () -> T): T {
        println("beforeCall()")
        return function.invoke()
    }

    @Test
    fun testAspect() {
        fun plus(x: Int, y: Int) = aspect { x + y }
        fun minus(x: Int, y: Int) = aspect { x - y }

        fun plus2(x: Int, y: Int) = aspectWithGeneric { (x + y).toLong() }
        fun minus2(x: Int, y: Int) = aspectWithGeneric { (x - y).toString() }
    }
}
