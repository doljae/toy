package com.designpattern.template

abstract class CaffeineBeverate {

    fun prepareRecipe() {
        boilWater()
        brew()
        pourIncCup()
        addCondiments()
    }

    abstract fun brew()
    abstract fun addCondiments()
    fun boilWater() {
        println("물 끓이는 중")
    }

    fun pourIncCup() {
        println("컵에 따르는 중")
    }
}