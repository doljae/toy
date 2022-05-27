package com.designpattern.strategy

class BowAndArrowBehavior : WeaponBehavior {
    override fun useWeapon() {
        println("use bow and arrow")
    }
}