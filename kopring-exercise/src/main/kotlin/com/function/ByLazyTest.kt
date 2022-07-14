package com.function

fun getTemperature(city: String): Double {
    println("fetch from webservice for$city")
    return 30.0
}


fun main() {
    val showTemperature = false
    val city = "Seoul"
    // && 연산자를 사용했기 때문에 선행 조건을 만족하지 않으면 뒷 조건은 확인하지 않는다.
    // getTemperature()이 호출되지 않는다.
    if (showTemperature && getTemperature(city) > 20) {
        println("Warm")
    } else {
        println("Nothing to report")
    }

    // 생각없이 리팩토링하면...
    val showTemperature2 = false
    val city2 = "Seoul"
    // 미리 메서드를 호출해 결과를 받기, 불필요한 API 호출
    val temperature = getTemperature(city)

    if (showTemperature && temperature > 20) {
        println("Warm")
    } else {
        println("Nothing to report")
    }

    // 진짜 리팩토링 버전
    val showTemperature3 = false
    val city3 = "Seoul"
    val temperatureLazy by lazy { getTemperature(city3) }

    if (showTemperature3 && temperatureLazy > 20) {
        println("Warm")
    } else {
        println("Nothing to report")
    }
}