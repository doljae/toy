package com.designpattern.decorator

class Milk(beverage: Beverage) : CondimentDecorator() {

    init {
        this.beverage = beverage
        this.description = beverage.description + ", 우유"
    }

    override fun cost(): Double {
        return beverage.cost() + .10
    }
}