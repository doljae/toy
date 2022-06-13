package com.designpattern.factory

abstract class Pizza {
    lateinit var name: String
    lateinit var dough: Dough
    lateinit var sauce: Sauce
    var veggies: MutableList<Veggies> = mutableListOf()
    lateinit var cheese: Cheese
    lateinit var pepperoni: Pepperoni
    lateinit var clams: Clams

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