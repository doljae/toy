package com.designpattern.strategy

class Queen(weaponBehavior: WeaponBehavior) : Character(weaponBehavior){
    override fun fight() {
        println("queen is fighting")
    }
}
