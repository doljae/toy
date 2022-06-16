package com.designpattern.singleton

// object는 무조건 싱글톤임
// 문제 object로 선언한 클래스는 실제 사용 유무와 상관없이 메모리에 올라간다
object singletonV1 {
    val name = "doljae"
}


fun main() {
    val singletonV1_1 = singletonV1
    val singletonV1_2 = singletonV1

    if (singletonV1_1.name == singletonV1_2.name) {
        println("동등성 비교 true")
    }

    if (singletonV1_1.name === singletonV1_2.name) {
        println("동일성 비교 true")
    }
}