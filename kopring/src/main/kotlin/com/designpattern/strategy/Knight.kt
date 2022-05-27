package com.designpattern.strategy

class Knight(weaponBehavior: WeaponBehavior) : Character(weaponBehavior){
    override fun fight() {
        println("knight is fighting")
    }
}
