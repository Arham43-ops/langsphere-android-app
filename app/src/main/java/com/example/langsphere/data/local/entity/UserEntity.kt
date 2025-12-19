package com.example.langsphere.data.local.entity

import androidx.room.*

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String, // Email or UUID
    val name: String,
    val email: String,
    val totalXp: Int = 0,
    val streakDays: Int = 0,
    val lastLessonDate: Long = 0, // Timestamp
    val profileImage: String? = null
)
