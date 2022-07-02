package com.designpattern.template

class Coffee : CaffeineBeverate() {
    override fun brew() {
        println("필터로 커피를 우려내는 중")
    }

    override fun addCondiments() {
        println("설탕과 우유를 추가하는 중")
    }
}