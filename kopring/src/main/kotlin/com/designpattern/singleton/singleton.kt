package com.designpattern.singleton

// object는 무조건 싱글톤임
// 문제 object로 선언한 클래스는 실제 사용 유무와 상관없이 메모리에 올라간다
object singletonV1 {
    val name = "doljae"
}

// 클래스가 호출될 때 name에 대한 String이 메모리에 올라간다.
object singletonV2 {
    val name by lazy { "doljae" }
}

// Java style의 싱글톤 구현
// private 생성자를 기본 생성자로 지정
class Singleton private constructor() {

    val name = "doljae"

    // 하나의 클래스에서 공통으로 사용하는 단 하나의 객체인 companion object를 사용
    companion object {
        // 필드 변수를 volitile로 선언해 이 값은 무조건 메모리에서 읽어오도록 설정
        @Volatile
        private var instance: Singleton? = null

        // java의 static 키워드를 사용한 것과 동일한 효과를 내어 정적 초기화를 한다
        // synchronized를 실제 객체를 생성하는 부분에서만 사용한 뒤
        @JvmStatic
        fun getInstance(): Singleton = instance ?: synchronized(this) {
            // instance의 유무를 확인 후 객체를 생성해 할당한다
            instance ?: Singleton().also { instance = it }
        }
    }
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

    val javaStyleSingleton1 = Singleton.getInstance()
    val javaStyleSingleton2 = Singleton.getInstance()

    if (javaStyleSingleton1.name == singletonV1_2.name) {
        println("동등성 비교 true")
    }

    if (javaStyleSingleton2.name === singletonV1_2.name) {
        println("동일성 비교 true")
    }
}