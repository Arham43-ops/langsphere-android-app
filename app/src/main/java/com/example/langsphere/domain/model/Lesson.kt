package com.example.langsphere.domain.model

data class Language(
    val id: String,
    val name: String,
    val flagEmoji: String,
    val greeting: String
)

data class Lesson(
    val id: String,
    val languageId: String,
    val title: String,
    val description: String,
    val phrases: List<Phrase>,
    val difficultyLevel: Int // 1-Beginner, 2-Intermediate, 3-Advanced
)

data class Phrase(
    val original: String,
    val translation: String,
    val audioUrl: String? = null,
    val context: String? = null
)
