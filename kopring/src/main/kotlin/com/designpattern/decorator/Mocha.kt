package com.designpattern.decorator

class Mocha(beverage: Beverage) : CondimentDecorator() {

    init {
        this.beverage = beverage
        this.description = beverage.description + ", 모카"
    }

    override fun cost(): Double {
        return beverage.cost() + .20
    }
}