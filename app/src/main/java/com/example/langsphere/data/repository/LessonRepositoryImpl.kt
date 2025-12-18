package com.example.langsphere.data.repository

import com.example.langsphere.domain.model.Language
import com.example.langsphere.domain.model.Lesson
import com.example.langsphere.domain.model.Phrase
import com.example.langsphere.domain.repository.LessonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LessonRepositoryImpl @Inject constructor() : LessonRepository {

    private val languages = listOf(
        Language("en", "English", "🇺🇸", "Hello"),
        Language("es", "Spanish", "🇪🇸", "Hola"),
        Language("fr", "French", "🇫🇷", "Bonjour"),
        Language("de", "German", "🇩🇪", "Hallo"),
        Language("it", "Italian", "🇮🇹", "Ciao"),
        Language("pt", "Portuguese", "🇧🇷", "Olá"),
        Language("ru", "Russian", "🇷🇺", "Привет"),
        Language("zh", "Mandarin", "🇨🇳", "你好"),
        Language("jp", "Japanese", "🇯🇵", "Konnichiwa")
    )

    private val lessons = listOf(
        // English
        Lesson("en1", "en", "Basics 1", "Essential greetings", listOf(
            Phrase("Hello", "Hello"),
            Phrase("Good morning", "Good morning"),
            Phrase("How are you?", "How are you?")
        ), 1),
        
        // Spanish
        Lesson("es1", "es", "Basics 1", "Common greetings", listOf(
            Phrase("Hello", "Hola"), 
            Phrase("Goodbye", "Adiós"),
            Phrase("Good morning", "Buenos días")
        ), 1),
        Lesson("es2", "es", "Basics 2", "Introductions", listOf(
            Phrase("My name is...", "Me llamo..."),
            Phrase("Nice to meet you", "Mucho gusto")
        ), 1),

        // French
        Lesson("fr1", "fr", "Basics 1", "Greetings", listOf(
            Phrase("Hello", "Bonjour"), 
            Phrase("Thank you", "Merci"),
            Phrase("Yes", "Oui")
        ), 1),

        // German
        Lesson("de1", "de", "Basics 1", "Start speaking German", listOf(
            Phrase("Hello", "Hallo"),
            Phrase("I am", "Ich bin"),
            Phrase("Thanks", "Danke")
        ), 1),

        // Italian
        Lesson("it1", "it", "Basics 1", "Benvenuto in Italia", listOf(
            Phrase("Hello", "Ciao"),
            Phrase("Good evening", "Buonasera"),
            Phrase("Please", "Per favore")
        ), 1),

        // Portuguese
        Lesson("pt1", "pt", "Basics 1", "Brazilian Basics", listOf(
            Phrase("Hello", "Olá"),
            Phrase("Everything good?", "Tudo bem?"),
            Phrase("Thank you", "Obrigado")
        ), 1),

        // Russian
        Lesson("ru1", "ru", "Basics 1", "Cyrillic Starters", listOf(
            Phrase("Hello", "Привет"),
            Phrase("Thank you", "Спасибо"),
            Phrase("Yes", "Да")
        ), 1),

        // Mandarin
        Lesson("zh1", "zh", "Basics 1", "Tones and Greetings", listOf(
            Phrase("Hello", "你好"),
            Phrase("Thank you", "谢谢"),
            Phrase("Goodbye", "再见")
        ), 1),

        // Japanese
        Lesson("jp1", "jp", "Basics 1", "Greetings", listOf(
            Phrase("Hello", "Konnichiwa"),
            Phrase("Thank you", "Arigato"),
            Phrase("Excuse me", "Sumimasen")
        ), 1)
    )

    override fun getLanguages(): Flow<List<Language>> = flow {
        emit(languages)
    }

    override fun getLessonsForLanguage(languageId: String): Flow<List<Lesson>> = flow {
        emit(lessons.filter { it.languageId == languageId })
    }

    override suspend fun getLessonDetails(lessonId: String): Lesson? {
        return lessons.find { it.id == lessonId }
    }
}
