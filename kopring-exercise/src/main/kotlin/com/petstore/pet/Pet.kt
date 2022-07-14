package com.petstore.pet

abstract class Pet(
    private val name: String,
    private val age: Int,
    private val gender: Enum<Gender>
) {
    abstract fun cry()

    override fun toString(): String {
        return "${this.javaClass.name}, name: $name, age: $age, gender: $gender"
    }

    fun canMate(partner: Pet): Boolean {
        return this.gender != partner.gender
    }
}