package com.designpattern.template

import java.time.LocalDateTime

open class Post(private var title: String, private var createdAt: LocalDateTime) {

    var age: Int = 5
        internal set

    fun update(title: String, time: LocalDateTime) {
        this.title = title
        this.createdAt = time
    }

    override fun toString(): String {
        this.age = 20
        return "Post(title='$title', createdAt=$createdAt)"
    }
}

class Child(title: String, createdAt: LocalDateTime) : Post(title, createdAt) {
    init {
        this.age = 20
    }

}

fun main() {
    val post = Post("apple", LocalDateTime.now())
    println(post)
    post.update("pear", LocalDateTime.MAX)
    println(post)
}