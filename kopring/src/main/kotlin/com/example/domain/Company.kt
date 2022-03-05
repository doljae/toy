package com.example.domain

class Company {
    var name: String = "defaultValue"
        private set

    fun setName(n: String) {
        name = n
    }
}
