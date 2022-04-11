package com.petstore

import com.petstore.pet.Breed
import com.petstore.pet.Dog
import com.petstore.pet.Gender
import com.petstore.store.DogStore

fun main() {
    val dogStore = DogStore()
    val dog = Dog("teddy", 10, Gender.MALE, Breed.HUSKY)

    dogStore.addPet(dog)

    println(dog)
    println(dogStore)
}