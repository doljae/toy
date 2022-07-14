package com.designpattern.template

class Tea : CaffeineBeverate() {
    override fun brew() {
        println("찻잎을 우려내는 중")
    }

    override fun addCondiments() {
        println("컵에 따르는 중")
    }
}