package com.designpattern.factory

interface PizzaIngredientFactory {

    fun createDough(): Dough
    fun createSauce(): Sauce
    fun createCheese(): Cheese
    fun createVeggies(): List<Veggies>
    fun createPepperoni(): Pepperoni
    fun createClam(): Clams
}

class Dough {

}

class Sauce {

}

class Cheese {

}

class Veggies {

}

class Pepperoni {

}

class Clams {

}
