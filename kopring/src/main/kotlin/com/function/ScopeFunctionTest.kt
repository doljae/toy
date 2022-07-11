package com.function

import kotlin.random.Random

class Player(var name: String, var age: Int)

// context object 자체를 반환한다
fun getRandomInt(): Int {
    return Random.nextInt(100).also {
        println("getRandomInt() generated value $it")
    }
}

fun main() {
    val player = Player("doljae", 30)

    player.run {
        println("이름 : $name") // this.name과 동일하다
    }

    player.let {
        println("이름: ${it.name}")
    }

    // apply, also는 Context Object 자체를 반환한다
    val numberList = mutableListOf<Double>()

    numberList.also {
        println("Popluating the list")
    }.apply {
        add(2.1)
        add(2.2)
        add(2.3)
    }.also {
        println("Sorting the list")
    }.sort()

    val numbers = mutableListOf("one", "two", "three")
    // let, run, with는 lambda 식을 반환한다
    val addNumbersList = numbers.run {
        this.add("four")
        this.add("five")
    }

    with(numbers) {
        val firstItem = first()
        val lastItem = last()
        println("첫번째 항목: $firstItem, 마지막 항목: $lastItem")
    }
}