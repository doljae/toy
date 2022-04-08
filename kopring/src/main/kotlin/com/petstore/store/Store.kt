package com.petstore.store

import com.petstore.pet.Pet

interface Store {

    fun addPet(pet: Pet)

    fun getPet(id: Long)

    fun removePet(id: Long)

    fun updatePet(id: Long)
}