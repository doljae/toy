package com.example.practice.functions

fun double(x: Int): Int {
    return 2 * x
}

val result = double(2)

fun powerOf(number: Int, exponent: Int): Int {
    return 10
}

fun read(b: ByteArray, off: Int = 0, len: Int = b.size) {

}

open class A {
    open fun foo(i: Int = 10) {}
}

class B : A() {
    override fun foo(i: Int) {

    }
}

fun reformat(
    str: String,
    normalizeCase: Boolean = true,
    upperCaseFirstLetter: Boolean = true,
    divideByCamelHumps: Boolean = false,
    wordSeparator: Char = ' '
) {
}

val result2 = reformat("apple")

fun printHello(name: String?): Unit {
    if (name != null)
        println("Hello $name")
    else
        println("Hi there!")
}
