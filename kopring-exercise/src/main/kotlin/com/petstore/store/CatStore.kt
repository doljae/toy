package com.petstore.store

import com.petstore.pet.Pet
import com.petstore.pet.Cat

class CatStore : Store {

    private var cages = mutableListOf<Cat>()

    override fun addPet(pet: Pet) {
        cages.add(pet as Cat)
    }

    override fun getPet(id: Int): Cat {
        return cages[id]
    }

    override fun removePet(id: Int) {
        cages.removeAt(id)
    }

    override fun updatePet(id: Int, pet: Pet) {
        cages[id] = pet as Cat
    }

    override fun toString(): String {
        return "${this.javaClass.name}, cages: $cages"
    }
}