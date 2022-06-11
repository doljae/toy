package com.designpattern.factory

open class Pizza {
}

class CheesePizza : Pizza() {

}

class GreekPizza : Pizza() {

}

class PepperoniPizza : Pizza() {

}

fun orderPizza(type: String): Pizza? {

    val pizza = Pizza()

    return when (type) {
        "cheese" -> CheesePizza()
        "greek" -> GreekPizza()
        "pepperoni" -> PepperoniPizza()
        else -> null
    }


}