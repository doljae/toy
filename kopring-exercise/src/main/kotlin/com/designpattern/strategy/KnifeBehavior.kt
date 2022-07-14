package com.designpattern.strategy

class KnifeBehavior : WeaponBehavior {
    override fun useWeapon() {
        println("use knife")
    }
}