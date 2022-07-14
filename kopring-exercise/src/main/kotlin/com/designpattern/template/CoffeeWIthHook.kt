package com.designpattern.template

class CoffeeWIthHook : CaffeineBeverate() {
    override fun brew() {
        println("필터로 커피를 우려내는 중")
    }

    override fun addCondiments() {
        println("설탕과 우유를 추가하는 중")
    }

    override fun customerWantsCondiments(): Boolean {
        val userInput = getUserInput()

        return userInput.equals("y", ignoreCase = true)
    }

    private fun getUserInput(): String {

        println("커피에 우유와 설탕을 넣을까요? Y or N")

        return readLine() ?: "no"
    }
}