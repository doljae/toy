package com.example.whiteboard.function

// getter, setter, toString, equals, hashcode를 신경안쓰고 올바르게 구현되어있음
data class People(var name: String, var age: Int, var gender: String) {}

fun main() {
    val people = People("doljae", 30, "male")
    val copiedPeople = people.copy(name = "eajlod")

    println(people)
    println(copiedPeople)

    // destructuring
    val (name, age, gender) = people
    println("$name $age $gender")
}