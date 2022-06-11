package com.designpattern.factory

fun main() {
    val nyPizzaStore = NYPizzaStore()

    val orderPizza = nyPizzaStore.orderPizza("cheese")
    println("에단이 주문한 ${orderPizza?.name}")
    println(orderPizza)

    val nonExistPizza = nyPizzaStore.orderPizza("null")
    println("에단이 주문한 존재하지 않는 피자, $nonExistPizza ${nonExistPizza?.name}")
    println(nonExistPizza)
}