package com.designpattern.factory

abstract class Pizza {
    var name: String? = null
    var dough: Dough? = null
    var sauce: Sauce? = null
    var veggies: MutableList<Veggies> = mutableListOf()
    var cheese: Cheese? = null
    var pepperoni: Pepperoni? = null
    var clams: Clams? = null

    abstract fun prepare()

    fun bake() {
        println("175도에서 25분간 굽기")
    }

    open fun cut() {
        println("피자를 사선으로 자르기")
    }

    fun box() {
        println("상자에 피자를 담기")
    }

    override fun toString(): String {
        return "Pizza(name='$name', dough='$dough', sauce='$sauce', toppings=$veggies)"
    }
}