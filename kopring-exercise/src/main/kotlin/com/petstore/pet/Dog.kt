package com.petstore.pet

class Dog(
    name: String,
    age: Int,
    gender: Enum<Gender>,
    private val breed: Enum<Breed>
) : Pet(name, age, gender) {
    override fun cry() {
        println("왈왈")
    }

    override fun toString(): String {
        return super.toString() + ", breed: $breed"
    }
}