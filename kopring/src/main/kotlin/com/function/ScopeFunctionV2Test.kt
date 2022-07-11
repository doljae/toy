package com.function

val numbers = mutableListOf("one", "two", "three", "four", "five")


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
}