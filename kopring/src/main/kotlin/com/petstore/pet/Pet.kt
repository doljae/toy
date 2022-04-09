package com.petstore.pet

abstract class Pet(val name: String,
                   val age: Int,
                   val gender: Enum<Gender>,
                   val breed: Enum<Breed>) {
}