package com.designpattern.factory

fun main() {
    val nyPizzaStore = NYPizzaStore()

    val orderPizza1 = nyPizzaStore.orderPizza("cheese")
    println("에단이 주문한 ${orderPizza1?.name}")
    println(orderPizza1)

    val orderPizza2 = nyPizzaStore.orderPizza("pepperoni")
    println("에단이 주문한 ${orderPizza2?.name}")
    println(orderPizza2)

    val nonExistPizza = nyPizzaStore.orderPizza("null")
    println("에단이 주문한 존재하지 않는 피자, $nonExistPizza ${nonExistPizza?.name}")
    println(nonExistPizza)
}