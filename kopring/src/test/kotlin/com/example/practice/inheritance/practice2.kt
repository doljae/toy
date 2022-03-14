package com.example.practice.inheritance

open class Base(val name: String) {
    init {
        println("Initializing a base class")
    }

    open val size: Int = name.length.also { println("Initializing a base class: $it") }
}

class Derived(name: String, val lastName: String) :
    Base(name.replaceFirstChar { it.uppercase() }.also { println("Argument for the base class: $it") }) {
    init {
        println("Initializing a derived class")
    }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in the derived class: $it") }
}

fun main() {
    val derived = Derived("myFirstName", "myLastName")
    println(derived.size)
}