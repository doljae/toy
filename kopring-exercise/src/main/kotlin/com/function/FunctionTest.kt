package com.example.whiteboard.function

data class Person(
    var name: String = "",
    var age: Int = 0,
    var temperature: Float = 36.5f
) {
    fun isSick(): Boolean = temperature > 37f
}

// apply는 수신 객체를 반환한다
val person = Person().apply {
    name = "doljae"
    age = 30
    temperature = 36.1f
}

// run은 마지막 라인을 반환한다
val isPersonSick = person.run {
    temperature = 38f
    isSick()
}

// with은 잘 안씀
val isPersonSick2 = with(person) {
    temperature = 38f
    isSick()
}

val nullPerson = null
val isReserved = nullPerson?.let(::reserveMovie)

fun reserveMovie(person: Person) {
    println("reserve movie")
}

var number = 3
fun getAndIncreaseNumber() = number.also { number++ }

// 객체에 대해서는 적합하지 않음
var person2 = Person("doljae", 30, 36f)
fun getAndIncreaseNumber2() = person2.also { person2.age = it.age + 1 }
fun getAndIncreaseNumberWithCopy() = person2.also { person2 = person2.copy(age = it.age + 1) }

fun main() {
    println("first number ${getAndIncreaseNumber()}")
    println("second number ${getAndIncreaseNumber()}")

    println("first number ${getAndIncreaseNumber2()}")
    println("second number ${getAndIncreaseNumber2()}")
}