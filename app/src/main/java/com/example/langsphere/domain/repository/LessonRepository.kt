package com.example.langsphere.domain.repository

import com.example.langsphere.domain.model.Language
import com.example.langsphere.domain.model.Lesson
import kotlinx.coroutines.flow.Flow

interface LessonRepository {
    fun getLanguages(): Flow<List<Language>>
    fun getLessonsForLanguage(languageId: String): Flow<List<Lesson>>
    suspend fun getLessonDetails(lessonId: String): Lesson?
}
