package com.example.inaction

data class User(val name: String, val id: Int) {
    override fun equals(other: Any?): Boolean = other is User && other.id == this.id
}

fun main() {
    val user = User("Alex", 1)
    println(user)

    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)

    println("user == secondUser : ${user == secondUser}")
    println("user == thirdUser : ${user == thirdUser}")

    println(user.hashCode())
    println(secondUser.hashCode())
    println(thirdUser.hashCode())

    println(user.copy())
    println(user == user.copy())
    println(user.copy("Max"))
    println(user.copy(id = 3))

    println("name== ${user.component1()}")
    println("id==${user.component2()}")

}