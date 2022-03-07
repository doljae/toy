package com.practice.classes

class practice2 {
}

class Person(
    val pets: MutableList<Pet>,
)

class Pet {
    constructor(owner: Person) {
        owner.pets.add(this)
    }
}