package com.example.practice.nullsafety

import org.w3c.dom.Node

var a: String = "abc"

var b: String? = "abc"

fun main() {
    var b: String? = "abc"
    b = null
    println(b)

    val l = if (b != null) b.length else -1

    val c: String? = "Kotlin"
    if (b != null && b.length > 0) {
        println("String of length ${b.length}")
    } else {
        println("Empty string")
    }

    // elvis

    val f: Int = if (b != null) b.length else -1
    val ff = b?.length ?: -1

}

fun foo(node: Node): String? {
    val parent = node.nodeName ?: return null
    val name = node.nodeName ?: throw IllegalArgumentException()
    return "test"
}