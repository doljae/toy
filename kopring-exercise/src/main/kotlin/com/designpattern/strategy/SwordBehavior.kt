package com.designpattern.strategy

class SwordBehavior : WeaponBehavior {
    override fun useWeapon() {
        println("use sword")
    }
}