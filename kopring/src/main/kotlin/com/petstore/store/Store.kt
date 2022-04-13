package com.petstore.store

import com.petstore.pet.Pet

interface Store {

    fun addPet(pet: Pet)

    fun getPet(id: Int): Pet

    fun removePet(id: Int)

    fun updatePet(id: Int, pet: Pet)
}