package com.example.practice.interfaces

interface MyInterface {
    val prop: Int
    val propertyWithImplementation: String
        get() = "foo"

    fun bar()
    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    override val prop: Int = 29

    override fun bar() {
        TODO("Not yet implemented")
    }

    override fun foo() {
        super.foo()
    }
}