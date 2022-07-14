package com.designpattern.factory

abstract class PizzaStore {

    fun orderPizza(type: String): Pizza? {

        val pizza = createPizza(type)

        // do something

        return pizza
    }

    protected abstract fun createPizza(type: String): Pizza?
}