package com.designpattern.factory

class PizzaStore(val factory: SimplePizzaFactory) {

    fun orderPizza(type: String): Pizza? {
        val pizza = factory.createPizza(type)

        // do something

        return pizza
    }
}