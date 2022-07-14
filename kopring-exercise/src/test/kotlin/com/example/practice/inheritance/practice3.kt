package com.example.practice.inheritance

open class Rectangle1 {
    open fun draw() {
        println("Drawing a rectangle")
    }

    val borderColor: String get() = "black"
}

interface Polygon1{
    fun draw(){}
}

class Square():Rectangle1(), Polygon1{
    override fun draw() {
        super<Rectangle1>.draw()
        super<Polygon1>.draw()
    }
}

class FilledRectangle1 : Rectangle1() {
    override fun draw() {
        super.draw()
        println("Filling the rectangle")
    }

    val fillColor: String get() = super.borderColor

    inner class Filler {
        fun fill() {
            println("Filling")
        }

        fun drawAndFill() {
            super@FilledRectangle1.draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle1.borderColor}")
        }
    }
}