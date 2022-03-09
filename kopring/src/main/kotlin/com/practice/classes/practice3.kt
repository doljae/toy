package com.practice.classes

abstract class Polygon{
    abstract fun draw()
}

class Rectangle:Polygon(){
    override fun draw() {
        TODO("Not yet implemented")
    }
}

open class Polygon2{
    open fun draw(){

    }
}