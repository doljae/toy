package com.designpattern.decorator

abstract class Beverage {

    var description: String = "제목 없음"

    abstract fun cost(): Double
}