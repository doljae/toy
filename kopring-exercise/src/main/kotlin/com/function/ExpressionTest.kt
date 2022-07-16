package com.example.whiteboard.function

fun isPromotionTarget(addToCart: Boolean, rates: Int): Boolean = when {
    rates < 2 -> false
    rates > 3 -> false
    rates == 3 -> true
    else -> addToCart
}

// else는 강력하게 컴파일러가 체크한다
// else는 반드시 마지막 줄에 위치해야한다
fun whatToDo(dayofWeek: Any) = when (dayofWeek) {
    "SAT", "SUN" -> "Relax"
    in listOf("Mon", "Tue", "Wed", "Thu") -> "Work hard"
    in 2..4 -> "Work hard"
    "Friday" -> "Party"
    is String -> "What?"
    else -> "No Clue"
}

// when 안의 변수를 안에서 동일 스코프 다른 곳에서 사용하지 못하게 막는다
fun systemInfo(): String =
    when (val numberOfCores = Runtime.getRuntime().availableProcessors()) {
        1 -> "1 CORE"
        in 2..6 -> "$numberOfCores multi core"
        else -> "wow"
    }