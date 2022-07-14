package com.example.inaction

fun rentPrice(standardDays: Int, festivalDays: Int, specialDays: Int) {
    val dayRates = object {
        var standard: Int = 30 * standardDays
        var festival: Int = 30 * festivalDays
        var special: Int = 30 * specialDays
    }

    val total = dayRates.standard + dayRates.festival + dayRates.special
    println("total price: $total")
}

class BigBen {
    companion object Bonger {
        fun getBongs(nTimes: Int) {
            for (i in 1..nTimes) {
                print("BONG ")
            }
        }
    }
}

fun main() {
    rentPrice(10, 2, 1)
    BigBen.getBongs(12)
}