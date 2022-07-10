package com.function

open class Fruit
class Apple : Fruit()
class Banana : Fruit()

// Array 는 mutable, List는 immutable
fun receiveFruitsV0(fruits: Array<Fruit>) {
    println("Number of fruits: ${fruits.size}")
}

// immutable하기 때문에 write가 없다는 것을 보장해서 위 메서드가 컴파일 에러가 없음
fun receiveFruits(fruits: List<Fruit>) {
    println("Number of fruits: ${fruits.size}")
}

// read-only는 안에 있는 값을 빼서(out) 읽고, write-only는 새로운 값을 넣어야(in) 한다
fun copyFromTo(from: Array<out Fruit>, to: Array<in Fruit>) {
    for (i in from.indices) {
        to[i] = from[i]
    }
}



fun main() {
    val arrayOf: List<Apple> = listOf(Apple())
    receiveFruits(arrayOf)

    val basket1 = Array<Fruit>(3) { Apple() }
    val basket2 = Array<Any>(3) { Any() }
    copyFromTo(basket1, basket2)
}