package com.designpattern.template

fun main() {
    // 템플릿 메소드 패턴은 알고리즘의 골격을 정의합니다.
    // 템플릿 메소드를 사용하면 알고리즘의 일부 단계를 서브클래스에서 구현할 수 있으며,
    // 알고리즘의 구조는 그대로 유지하면서 알고리즘의 특정 단계를 서브클래스에서 재정의할 수도 있습니다.
    val tea = Tea()
    tea.prepareRecipe()

    val coffee = Coffee()
    coffee.prepareRecipe()
}