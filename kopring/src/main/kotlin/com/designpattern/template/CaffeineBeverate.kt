package com.designpattern.template

abstract class CaffeineBeverate {

    fun prepareRecipe() {
        boilWater()
        brew()
        pourIncCup()
        if (customerWantsCondiments()) addCondiments() else println("추가하지 않습니다")
    }

    open fun customerWantsCondiments() = true
    abstract fun brew()
    abstract fun addCondiments()
    fun boilWater() {
        println("물 끓이는 중")
    }

    fun pourIncCup() {
        println("컵에 따르는 중")
    }
}