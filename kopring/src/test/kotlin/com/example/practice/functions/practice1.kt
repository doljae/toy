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

fun double1(x: Int): Int = x * 2
fun double2(x: Int) = x * 2

fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) {
        result.add(t)
    }
    return result
}

val list1 = asList(1, 2, 3)

val a = arrayOf(1, 2, 3)
val list2 = asList(-1, 0, *a, 4)

val b = intArrayOf(1, 2, 3)
val list = asList(-1, 0, *b.toTypedArray(), 4)