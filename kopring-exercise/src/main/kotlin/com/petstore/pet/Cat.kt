package com.petstore.pet

class Cat(
    name: String,
    age: Int,
    gender: Enum<Gender>,
    private val breed: Enum<Breed>
) : Pet(name, age, gender) {
    override fun cry() {
        println("야옹")
    }

    override fun toString(): String {
        return super.toString() + ", breed: $breed"
    }
}