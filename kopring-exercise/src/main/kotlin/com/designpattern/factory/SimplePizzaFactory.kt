//package com.designpattern.factory
//
//class SimplePizzaFactory {
//
//    fun createPizza(type: String): Pizza? {
//
//        return when (type) {
//            "cheese" -> CheesePizza()
//            "pepperoni" -> PepperoniPizza()
//            else -> null
//        }
//    }
//}