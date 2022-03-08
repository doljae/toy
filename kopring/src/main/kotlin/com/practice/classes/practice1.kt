package com.practice.classes;


//private class Person constructor(firstName: String) {}
private class Person2(name: String) {
    val customerKey = name.uppercase()
}

private class Person3(val firstName: String, val lastName: String, var age: Int)
private class Person4(val firstName: String, val lastName: String, var isEmployed: Boolean = true)
private class Person5(
    val firstName: String,
    val lastName: String,
    var age: Int,
)

private class Person6 public constructor(name: String)

private class Empty

private class InitOrderDemo(name: String) {
    val firstProp = "First Property $name".also(::println)

    init {
        println("First initializer block, then print $name")
    }

    val secondProp = "Second Property $name".also(::println)

    init {
        println("Second initializer block, then print $name")
    }
}

fun main() {
    val initOrderDemo = InitOrderDemo("tempName")
}