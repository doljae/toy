package com.example.practice.extension

//fun MutableList<Int>.swap(index1: Int, index2: Int) {
//    val tmp = this[index1]
//    this[index1] = this[index2]
//    this[index2] = tmp
//}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun main() {
    val list = mutableListOf(1, 2, 3)
    list.swap(0, 2)

    printClassName(Rectangle())

    Example().printFunctionType()
    Example().printFunctionType(1)

    MyClass.printCompanion()
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

fun Example.printFunctionType(i: Int) {
    println("Execution")
}

fun Any?.toString(): String {
    if (this == null) return "null"

    return toString()
}

val <T> List<T>.lastIndex: Int
    get() = size - 1

class MyClass {
    companion object {}
}

fun MyClass.Companion.printCompanion() {
    println("companion")
}