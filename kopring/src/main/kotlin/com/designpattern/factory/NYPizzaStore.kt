package com.designpattern.factory

class NYPizzaStore : PizzaStore() {
    override fun createPizza(type: String): Pizza? {

        val nyPizzaIngredientFactory = NYPizzaIngredientFactory()

        return when (type) {
            "cheese" -> CheesePizza(nyPizzaIngredientFactory)
            "pepperoni" -> PepperoniPizza(nyPizzaIngredientFactory)
            else -> null
        }
    }
}