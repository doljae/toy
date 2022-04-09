package com.petstore

import com.petstore.pet.Dog
import com.petstore.store.DogStore

fun main() {
    val dogStore = DogStore()
    val dog = Dog()

    dogStore.addPet(dog)
}