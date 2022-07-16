package com.example.whiteboard.function

object Foo {
    fun bar() = "bar"
}

class Outer {
    val internal = object {
        val property = "property"
    }
}

class Cake(val flavor: String) {
    companion object Factory {
        fun strawberry(): Cake = Cake("딸기")
        fun cheese(): Cake = Cake("치즈")
    }
}

fun main() {
    val expression = object {
        val bar = "example"
        fun foo() = "object"
    }

    println("${expression.foo()} ${expression.bar}")
    println(Foo.bar())
    // internal.property does not expose
    println(Outer().internal)
    println(Cake.strawberry())
    // companion object의 이름은 생략 가능
    println(Cake.Factory.strawberry())
    println(Cake.cheese())
    println(Cake.Factory.cheese())
}