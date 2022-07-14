package com.designpattern.decorator

class HouseBlend : Beverage() {

    init {
        this.description = "하우스 블렌드 커피"
    }

    override fun cost(): Double {
        return .89
    }
}