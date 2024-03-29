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

var counter = 0
    set(value) {
        if (value >= 0)
            field = value
    }

fun main() {
    counter = 10
    println(counter)

    counter = -10
    println(counter)
}

private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if (_table == null) {
            _table = HashMap()
        }
        return _table ?: throw AssertionError("Set to null by another thread")
    }

const val SUBSYSTEM_DEPRECATES: String = "This is test"

@Deprecated(SUBSYSTEM_DEPRECATES)
fun foo() {

}

public class MyTest {
    lateinit var subject: String

    fun setUp() {
        subject = "late init"
    }

    fun test() {
        println(subject.length)
    }

}
