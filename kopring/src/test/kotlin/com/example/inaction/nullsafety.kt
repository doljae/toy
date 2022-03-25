package com.example.inaction

fun main() {
    var neverNull: String = "This can't be null"
    // the line below makes exception
    // neverNull = null

    var nullable: String? = "You can keep a null here"
    nullable = null

    var inferredNonNull = "The compiler assumes non-null"
    // the line below makes exception
    // inferredNonNull = null

    println(strLength(neverNull))
    // the line below makes exception
    // println(strLength(nullable))
}

fun strLength(notNull: String): Int {
    return notNull.length
}

fun describeString(maybeString: String?): String {
    return if (maybeString != null && maybeString.isNotEmpty()) {
        "String of length ${maybeString.length}"
    } else {
        "Empty or null string"
    }
}