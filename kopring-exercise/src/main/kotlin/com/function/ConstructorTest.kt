package com.example.whiteboard.function

class InitOrderDemo(name: String) {
    // 클래스 내부에서 property 선언, init 블록은 동일한 우선순위를 가진다
    // 즉 순서대로 처리된다
    val firstProperty = "First property: $name".also { str -> println(str) }

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

class InitOrderDemoV2(
    val firstName: String,
    val lastName: String,
    var age: Int
)

class PersonWithPC(val name: String) {
    var age: Int = 30

    // secondary constrcutor는 반드시 primary constructor를 위임받아야한다
    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }
}

class PersonWithPcV2(val name: String) {
    var age: Int
    var switch: Boolean = true

    // init 블록은 primary constructor의 일부여서 secondary constructor보다 먼저 실행된다.
    init {
        age = 30
    }

    constructor(name: String, age: Int) : this(name) {
        println(this.age)
        this.age = age
        println(this.age)
    }

    private constructor(name: String, switch: Boolean) : this(name) {
        this.switch = switch
    }


}

fun main() {
    val initOrderDemo = InitOrderDemo("doljae")

    val person1 = PersonWithPcV2("doljae", 30)
}