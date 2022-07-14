package com.designpattern.strategy

abstract class Character(var weaponBehavior: WeaponBehavior) {

    abstract fun fight()

    fun useWeapon() {
        weaponBehavior.useWeapon()
    }

    fun setWeapon(weaponBehavior: WeaponBehavior) {
        this.weaponBehavior = weaponBehavior
    }
}