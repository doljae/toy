package com.designpattern.factory

abstract class Pizza(
    val name: String,
    val dough: String,
    val sauce: String,
    val toppings: MutableList<String> = mutableListOf()
) {

    fun prepare() {
        println("준비 중: $name")
        println("도우를 돌리는 중: $dough")
        println("소스를 뿌리는 중: $sauce")
        println("토핑을 올리는 중: $toppings")
    }

    fun bake() {
        println("175도에서 25분간 굽기")
    }

    open fun cut() {
        println("피자를 사선으로 자르기")
    }

    fun box() {
        println("상자에 피자를 담기")
    }

    override fun toString(): String {
        return "Pizza(name='$name', dough='$dough', sauce='$sauce', toppings=$toppings)"
    }
}

class CheesePizza : Pizza("치즈 피자", "치즈 피자 도우", "토마토 소스") {
    init {
        toppings.add("모짜렐라 치즈")
    }
}

class GreekPizza : Pizza("그리스 피자", "그리스 피자 도우", "핫 소스") {
    init {
        toppings.add("모짜렐라 치즈")
    }
}

class PepperoniPizza : Pizza("페페로니 피자", "페페로니 도우", "핫 소스") {
    init {
        toppings.add("에멘탈 치즈")
    }

    override fun cut() {
        println("네모난 모양으로 자르기")
    }
}