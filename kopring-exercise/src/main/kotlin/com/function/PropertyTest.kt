package com.function

// kotlin의 class property는 반드시 초기화되거나 추상화되어야한다
class PersonPerson {
    // 선언과 동시에 초기화하기
    var name: String = "doljae"
    val age: Int?
    val address: String?

    // init 블록에서 초기화하기
    init {
        age = 30
    }

    // secondary constructor로 초기화하기
    constructor() {
        address = "my address"
    }

}

// var은 getter, setter가, val은 getter만 자동 생성된다
class MyList {
    var size: Int? = 0
    val isEmpty: Boolean
        // get 함수에 로직을 추가할 수 있다
        get() = this.size == 0

    var name: String = "not assigned"
        // set 함수는 입력된 값을 value, 해당 프로퍼티의 값을 value라는 예약어를 사용한다
        // backing property
        set(value) {
            field = "Dev. $value"
        }
}

fun main() {
    val personPerson = PersonPerson()
    personPerson.name = "fixed name"

    println(personPerson.name)
}