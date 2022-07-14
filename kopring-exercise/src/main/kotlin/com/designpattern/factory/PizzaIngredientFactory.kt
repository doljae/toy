package com.designpattern.factory

interface PizzaIngredientFactory {

    fun createDough(): Dough
    fun createSauce(): Sauce
    fun createCheese(): Cheese
    fun createVeggies(): List<Veggies>
    fun createPepperoni(): Pepperoni
    fun createClam(): Clams
}

open class Dough {

}

class ThinCrustDough : Dough() {

}

open class Sauce {

}

class MarinaraSauce : Sauce() {

}

open class Cheese {

}

class ReggianoCheese : Cheese() {

}

open class Veggies {

}

class Garlic : Veggies() {

}

class Onion : Veggies() {

}

class Mushroom : Veggies() {

}

open class Pepperoni {

}

class SlicedPepperoni : Pepperoni() {

}

open class Clams {

}

class FreshClams : Clams() {

}
