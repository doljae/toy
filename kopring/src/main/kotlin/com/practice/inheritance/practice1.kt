package com.practice.inheritance

import javax.naming.Context
import javax.swing.text.AttributeSet

open class Example

class Apple : Example() {

}

//open class Base(p: Int)
//class Derived(p: Int) : Base(p)

open class View {
    constructor(ctx: Context)
    constructor(ctx: Context, attrs: AttributeSet)
}

class MyView : View {
    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
}

open class Shape {
    open val vertexCount: Int = 0

    open fun draw() {}
    fun fill() {}
}

class Circle : Shape() {
    override fun draw() {
        super.draw()
    }
}

open class Rectangle() : Shape() {
    override val vertexCount: Int = 4
    final override fun draw() {
        super.draw()
    }
}

interface ShapeInterface {
    val vertexCount: Int
}

class Rectangle2(override val vertexCount: Int = 4) : ShapeInterface

class Polygon : ShapeInterface {
    override var vertexCount: Int = 4
}