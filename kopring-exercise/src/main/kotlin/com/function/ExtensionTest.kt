package com.example.whiteboard.function

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun <T> MutableList<T>.swapGeneric(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

open class Shape
class Rectangle : Shape()

fun Shape.getName() = "Shape"
fun Rectangle.getName() = "Rectangle"
fun printClassName(s: Shape) {
    println(s.getName())
}

class Example {
    fun printFunctionType() {
        println("Class method")
    }
}

fun Example.printFunctionType() {
    println("Extension function")
}

val <T> List<T>.lastIndex: Int
    get() = size - 1

fun Any?.toString(): String {
    return this?.toString() ?: "null"
}

class Host(val hostname: String) {
    fun printHostname() {
        println(hostname)
    }
}

class Connection(val host: Host, val port: Int) {
    fun printPort() {
        println(port)
    }

    fun Host.printConnectionString() {
        printHostname()
        print(":")
        printPort()
    }

    fun connection() {
        host.printConnectionString()
    }
}

fun main() {
    val list = mutableListOf(1, 2, 3)
    list.swap(0, 2)

    list.swapGeneric(2, 0)
    println(list)

    printClassName(Rectangle())
    Example().printFunctionType()

    println(list.lastIndex)

    Connection(Host("kotl.in"), 443).connection()
}