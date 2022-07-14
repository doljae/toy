package com.example.inaction

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0xFF0000),
    BLUE(0xFF0000),
    YELLOW(0xFF0000),
    ;

    fun containsRed() = this.rgb and 0xFF0000 != 0
}