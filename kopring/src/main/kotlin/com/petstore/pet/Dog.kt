package com.petstore.pet

class Dog(name: String, age: Int, gender: Enum<Gender>, breed: Breed) : Pet(name, age, gender) {

}