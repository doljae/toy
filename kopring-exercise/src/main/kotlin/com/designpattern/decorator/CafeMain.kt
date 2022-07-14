package com.designpattern.decorator

fun main() {
    val houseBlend = HouseBlend()
    println(houseBlend.description + " $" + houseBlend.cost())

    val houseBlendWithMocha = Mocha(houseBlend)
    println(houseBlendWithMocha.description+ " $" + houseBlendWithMocha.cost())
    val houseBlendWithTwoMocha = Mocha(houseBlendWithMocha)
    val houseBlendWithTwoMochaMilk = Mocha(houseBlendWithTwoMocha)
    println(houseBlendWithTwoMochaMilk.description + " $" + houseBlendWithTwoMochaMilk.cost())


    val espresso = Espresso()
    println(espresso.description + " $" + espresso.cost())

    val espressoWithOneWhipOneMilkOneMocha = Whip(Milk(Mocha(Mocha(espresso))))
    println(espressoWithOneWhipOneMilkOneMocha.description + " $" + espressoWithOneWhipOneMilkOneMocha.cost())

}