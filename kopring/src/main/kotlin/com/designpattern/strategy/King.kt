package com.designpattern.strategy

class King(weaponBehavior: WeaponBehavior) : Character(weaponBehavior){
    override fun fight() {
        println("king is fighting")
    }
}
