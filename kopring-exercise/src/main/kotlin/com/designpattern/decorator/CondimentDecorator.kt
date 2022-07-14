package com.designpattern.decorator

abstract class CondimentDecorator : Beverage() {

    lateinit var beverage: Beverage
}