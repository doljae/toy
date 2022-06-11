package com.designpattern.factory

class NYPizzaStore : PizzaStore() {
    override fun createPizza(type: String): Pizza? {

        return when (type) {
            "cheese" -> CheesePizza()
            "greek" -> GreekPizza()
            "pepperoni" -> PepperoniPizza()
            else -> null
        }
    }
}