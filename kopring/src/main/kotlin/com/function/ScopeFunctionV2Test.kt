package com.function

val numbers = mutableListOf("one", "two", "three", "four", "five")

class Point(var x: Int, var y: Int)

fun main() {
    // without let
    val resultList = numbers.map { it.length }.filter { it > 3 }
    println(resultList)

    // let은 결과값에 하나 이상의 함수를 호출하는 경우 사용한다.
    // with let
    val resultListV2 = numbers.map { it.length }.filter { it > 3 }.let { println(it) }
    // resultV2와 같다
    val resultListV3 = numbers.map { it.length }.filter { it > 3 }.let(::println)

    // null이 아닌 값으로 코드블록을 실행하고 싶을때 ?와 조합해 종종사용함
    val str: String? = "Hello"
    val length = str?.let { println("let()호출 $it") }

    // with
    // 이미 생성된 context object 객체를 인자로 받아서 사용하는 것이 효율적일 때는 with를 사용하면 더 좋습니다.
    val numbers = mutableListOf("one", "two", "three")
    with(numbers) {
        println("'with'은 $this 로 참조합니다.")
        println("${size}개의 요소를 포함합니다.")
    }

    val firstAndLast = with(numbers) {
        println("'with'은 $this 로 참조합니다.")
        println("${size}개의 요소를 포함합니다.")
    }
    println(firstAndLast)

    // run
    val point = Point(10, 20)
    val width = point.x * 0.5

    val widthV2 = run {
        val point = Point(10, 20)
        // 마지막 줄의 값이 반환된다
        point.x * 0.5
    }

    // 요렇게도 사용이 가능하다
    // ?를 이용한 safe call도 가능하기 때문에 with 보다 자주 사용된다.
    val widthV3 = point?.run { x * 0.5 }
}