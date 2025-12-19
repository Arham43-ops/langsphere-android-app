package com.example.langsphere.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val id: String, // e.g., "first_lesson"
    val title: String,
    val description: String,
    val iconResName: String, // e.g., "ic_star"
    val isUnlocked: Boolean = false,
    val userId: String // Foreign Key logic handled manually or via relations
)
