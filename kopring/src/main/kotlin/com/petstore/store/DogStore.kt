package com.petstore.store

import com.petstore.pet.Dog
import com.petstore.pet.Pet

class DogStore : Store {

    private var cages = mutableListOf<Dog>()

    override fun addPet(pet: Pet) {
        cages.add(pet as Dog)
    }

    override fun getPet(id: Int): Dog {
        return cages[id]
    }

    override fun removePet(id: Int) {
        cages.removeAt(id)
    }

    override fun updatePet(id: Int, pet: Pet) {
        cages[id] = pet as Dog
    }
}