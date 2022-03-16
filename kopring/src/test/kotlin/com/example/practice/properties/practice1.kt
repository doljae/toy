package com.example.practice.properties

class Address {
    var name: String = "holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}

fun copyAddress(address: Address): Address {
    val result = Address()
    result.name = address.name
    result.street = address.street
    result.city = address.city
    result.state = address.state
    result.zip = address.zip

    return result
}

var initialized = 1
const val inferredType = 1

class Rectangle(val width: Int, val height: Int) {
    val area: Int
        get() = this.width * this.height

    val area2 get() = this.width * this.height
}

var setterVisibility: String = "abc"
    private set

var setterWithAnnotation: Any? = null