package com.designpattern.factory

class CheesePizza(private val pizzaIngredientFactory: PizzaIngredientFactory) : Pizza() {
    init {
        name = "치즈 피자"
    }

    override fun prepare() {
        println("준비 중...$name")
        dough = pizzaIngredientFactory.createDough()
        sauce = pizzaIngredientFactory.createSauce()
        cheese = pizzaIngredientFactory.createCheese()
    }
}

class PepperoniPizza(private val pizzaIngredientFactory: PizzaIngredientFactory) : Pizza() {
    init {
        name = "페페로니 피자"
    }

    override fun prepare() {
        println("준비 중...$name")
        dough = pizzaIngredientFactory.createDough()
        sauce = pizzaIngredientFactory.createSauce()
        cheese = pizzaIngredientFactory.createCheese()
        pepperoni = pizzaIngredientFactory.createPepperoni()
        veggies.add(Mushroom())
        veggies.add(Onion())
    }
}