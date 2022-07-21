package com.designpattern.proxy

class PersonImpl(
    private var name: String,
    private var gender: String,
    private var interests: String,
    private var rating: Int,
    private var ratingCount: Int = 0
) : Person {

    override fun getName(): String {
        return name
    }

    override fun getGender(): String {
        return gender
    }

    override fun getInterests(): String {
        return interests
    }

    override fun getGeekRating(): Int {
        return if (ratingCount == 0) 0 else rating / ratingCount
    }

    override fun setName(name: String) {
        this.name = name
    }

    override fun setGender(gender: String) {
        this.gender = gender
    }

    override fun setInterests(interests: String) {
        this.interests = interests
    }

    override fun setGeekRating(rating: Int) {
        this.rating = rating
    }
}