package com.example.langsphere.data.model

data class Language(
    val id: String,
    val name: String,
    val icon: String,
    val level: String,
    val progress: Int,
    val totalLessons: Int
)

data class Lesson(
    val id: String,
    val languageId: String,
    val number: Int,
    val title: String,
    val duration: String,
    val phraseCount: Int,
    val status: String // "completed", "in_progress", "locked"
)

data class Phrase(
    val id: String,
    val lessonId: String,
    val originalText: String,
    val translatedText: String,
    val audioUrl: String? = null
)
