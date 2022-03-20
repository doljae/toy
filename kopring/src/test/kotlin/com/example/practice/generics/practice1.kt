package com.example.practice.generics

class Box<T>(t: T) {
    var value = t
}

val box: Box<Int> = Box<Int>(1)
val box2 = Box(1)