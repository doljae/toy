package com.example.practice.dataclass;

data class User(val name: String, val age: Int)

data class User2(val name: String = "", val age: Int = 0)

data class Person(val name: String) {
    var age: Int = 0
}


fun main() {
    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 10

    val oldPerson1 = person1.copy(name = "oldJohn")

    val jane = User2("Jane", 35)
    val (name, age) = jane

    println("$name, $age years of age")

    println(person1 == person2)
}