package com.example.practice.qualified

class A {
    inner class B {
        fun Int.foo() {
            val a = this@A
            val b = this@B

            val c = this
            val c1 = this@foo

            val funLit = lambda@ fun String.() {
                val d = this // funLit's receiver
            }

            val funLit2 = { _: String ->
                val d1 = this
            }
        }
    }
}