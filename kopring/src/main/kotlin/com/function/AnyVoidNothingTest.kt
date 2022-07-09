package com.function

// Any는 최상위 클래스
val greeting: Any = "Hello world"

// Unit은 void와 동일
fun returnUnit(): Unit {

}

fun returnUnitExplicitly1(): Unit {
    return
}


fun returnUnitExplicitly2(): Unit {
    return Unit
}

val unit: Any = Unit

// Nothing은 2가지 경우에 사용한다
// 함수가 리턴 될 일이 없는 경우, 컴파일 에러를 볼 수 있다
fun infiniteLoop(): Nothing {
    while (true) {
        println("Hi there!")
    }
}

// 예외를 던지는 함수의 리턴 타입
// 함수의 리턴타입이 있다 -> 정상적으로 실행되었다
// 없다 -> 함수가 리턴되었다라고 보지 않는다
fun throwExceptionFunction(): Nothing {
    throw RuntimeException()
}

// Nothing?도 가능하다
fun throwExceptionOrNot(bool: Boolean): Nothing? {
    return if (bool) throw RuntimeException() else {
        println("Exception not thrown: ")
        null
    }
}

// expression
val nullableValue: String? = null
val nullableString: String? = null

// Nothing은 모든 클래스의 서브클래스임
val value = nullableString ?: throw RuntimeException()

val value2: Int = nullableValue?.toInt() ?: throw RuntimeException()