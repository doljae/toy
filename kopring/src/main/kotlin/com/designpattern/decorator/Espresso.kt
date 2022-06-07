package com.designpattern.decorator

class Espresso : Beverage() {

    init {
        this.description = "에스프레소"
    }

    override fun cost(): Double {
        return 1.99
    }
}