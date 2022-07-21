package com.designpattern.proxy

interface Person {

    fun getName(): String
    fun getGender(): String
    fun getInterests(): String
    fun getGeekRating(): Int

    fun setName(name: String): Unit
    fun setGender(gender: String): Unit
    fun setInterests(interests: String): Unit
    fun setGeekRating(rating: Int): Unit
}