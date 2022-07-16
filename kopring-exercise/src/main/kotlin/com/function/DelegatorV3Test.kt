package com.example.whiteboard.function

import kotlin.reflect.KProperty

// 변수 위임
class PoliteString(var content: String) {
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        content = value
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = content.replace("stupid", "s*****")
}

// 프로퍼티 위임
class PoliteStringForMap(val datasource: MutableMap<String, Any>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) =
        (datasource[property.name] as? String)?.replace("stupid", "s*****") ?: ""

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        datasource[property.name] = value
    }
}

class PostComment(datasource: MutableMap<String, Any>) {
    val title: String by datasource
    var likes: Int by datasource
    val comment: String by PoliteStringForMap(datasource)

    override fun toString(): String = "Title: $title, Likes: $likes, Comment: $comment"
}

fun main() {
    var commentWithDelegation: String by PoliteString("nice message")
    println(commentWithDelegation)
    commentWithDelegation = "this is stupid"
    println(commentWithDelegation)

    println(" ====================================== ")
    val data = mutableMapOf<String, Any>(
        "title" to "Using Delegation",
        "likes" to 2,
        "comment" to "Keep it simple, stupid"
    )

    val post = PostComment(data)
    post.likes++
    println(post)
    println(post.comment)
}