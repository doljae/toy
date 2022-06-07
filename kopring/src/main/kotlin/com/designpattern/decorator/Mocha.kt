package com.designpattern.decorator

class Mocha(beverage: Beverage) : CondimentDecorator() {

    init {
        this.beverage = beverage
    }

    override fun cost(): Double {
        return beverage.cost() + .20
    }

    override fun description(): String {
        return beverage.description + ", 모카"
    }
}