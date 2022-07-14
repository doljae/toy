package com.designpattern.decorator

class Whip(beverage: Beverage) : CondimentDecorator() {

    init {
        this.beverage = beverage
        this.description = beverage.description + ", 휘핑크림"
    }

    override fun cost(): Double {
        return beverage.cost() + .15
    }
}