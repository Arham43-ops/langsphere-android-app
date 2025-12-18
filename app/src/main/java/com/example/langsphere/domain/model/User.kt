package com.example.langsphere.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val xp: Int = 0,
    val streak: Int = 0
)
