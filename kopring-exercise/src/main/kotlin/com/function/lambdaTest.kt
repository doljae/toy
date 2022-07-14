package com.function

import java.util.*

// kotlin의 함수는 일급함수 -> 함수를 변수, 파라미터, 리턴 등에 다 사용이 가능하다
val capitalize =
    { input: String -> input.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }

// 함수를 파라미터로 받는 함수
fun transform(str: String, fn: (String) -> String): String = fn(str)

fun main() {
    println(capitalize("hello world"))
    println(transform("hello world", capitalize))
}