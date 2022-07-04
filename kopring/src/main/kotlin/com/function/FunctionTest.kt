package com.function

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



