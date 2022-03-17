package com.example.practice.interfaces

interface MyInterface {
    fun bar()
    fun foo() {}
}

class Chile:MyInterface{
    override fun bar() {
        TODO("Not yet implemented")
    }

    override fun foo() {
        super.foo()
    }
}