package com.example.langsphere.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.langsphere.data.model.Language
import com.example.langsphere.data.model.Lesson
import com.example.langsphere.data.model.Phrase

class MainViewModel : ViewModel() {

    private val _languages = MutableLiveData<List<Language>>()
    val languages: LiveData<List<Language>> = _languages

    private val _lessons = MutableLiveData<List<Lesson>>()
    val lessons: LiveData<List<Lesson>> = _lessons

    private val _phrases = MutableLiveData<List<Phrase>>()
    val phrases: LiveData<List<Phrase>> = _phrases

    init {
        loadMockLanguages()
    }

    private fun loadMockLanguages() {
        _languages.value = listOf(
            Language("1", "Spanish", "🇪🇸", "Intermediate", 45, 12),
            Language("2", "French", "🇫🇷", "Beginner", 0, 10),
            Language("3", "German", "🇩🇪", "Beginner", 15, 8),
            Language("4", "Japanese", "🇯🇵", "Advanced", 75, 20)
        )
    }

    fun loadLessonsForLanguage(languageId: String) {
        // Mock data for lessons
        _lessons.value = listOf(
            Lesson("101", languageId, 1, "Greetings", "10 min", 5, "completed"),
            Lesson("102", languageId, 2, "Numbers", "12 min", 8, "in_progress"),
            Lesson("103", languageId, 3, "Food & Drinks", "15 min", 10, "locked"),
            Lesson("104", languageId, 4, "Colors", "8 min", 6, "locked")
        )
    }

    fun loadPhrasesForLesson(lessonId: String) {
        // Mock data for phrases
        _phrases.value = listOf(
            Phrase("201", lessonId, "Hello", "Hola"),
            Phrase("202", lessonId, "Good morning", "Buenos días"),
            Phrase("203", lessonId, "Thank you", "Gracias"),
            Phrase("204", lessonId, "Goodbye", "Adiós"),
            Phrase("205", lessonId, "Please", "Por favor")
        )
    }
}
