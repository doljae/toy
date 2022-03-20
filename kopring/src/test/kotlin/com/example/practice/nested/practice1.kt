package com.example.practice.nested

class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }

    inner class Inner {
        fun foo() = bar
    }
}

val demo = Outer.Nested().foo()
val demo2 = Outer().Inner().foo()

interface OuterInterface {
    class InnerClass
    interface InnerInterface
}

class OuterClass {
    class InnerClass
    interface InnerInterface
}

