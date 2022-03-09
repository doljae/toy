package com.practice.classes



class Person(
    val pets: MutableList<Pet>,
)

class Pet(owner: Person) {
    init {
        owner.pets.add(this)
    }
}
