package com.example.domain

import javax.persistence.*

@Entity
@Table
class Cat(name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
}