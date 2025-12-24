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
        // ===== SPANISH (10 lessons) =====
        Lesson("es1", "es", "Basics 1", "Essential Greetings", listOf(
            Phrase("Hello", "Hola"),
            Phrase("Goodbye", "Adiós"),
            Phrase("Good morning", "Buenos días"),
            Phrase("Good afternoon", "Buenas tardes"),
            Phrase("Good night", "Buenas noches"),
            Phrase("See you later", "Hasta luego"),
            Phrase("Please", "Por favor"),
            Phrase("Thank you", "Gracias")
        ), 1),
        Lesson("es2", "es", "Basics 2", "Introductions", listOf(
            Phrase("My name is", "Me llamo"),
            Phrase("What is your name?", "¿Cómo te llamas?"),
            Phrase("Nice to meet you", "Mucho gusto"),
            Phrase("How are you?", "¿Cómo estás?"),
            Phrase("I am fine", "Estoy bien"),
            Phrase("And you?", "¿Y tú?"),
            Phrase("Where are you from?", "¿De dónde eres?"),
            Phrase("I am from", "Soy de")
        ), 1),
        Lesson("es3", "es", "Numbers 1-10", "Counting Basics", listOf(
            Phrase("One", "Uno"),
            Phrase("Two", "Dos"),
            Phrase("Three", "Tres"),
            Phrase("Four", "Cuatro"),
            Phrase("Five", "Cinco"),
            Phrase("Six", "Seis"),
            Phrase("Seven", "Siete"),
            Phrase("Eight", "Ocho"),
            Phrase("Nine", "Nueve"),
            Phrase("Ten", "Diez")
        ), 1),
        Lesson("es4", "es", "Food & Dining", "At the restaurant", listOf(
            Phrase("I am hungry", "Tengo hambre"),
            Phrase("I am thirsty", "Tengo sed"),
            Phrase("Water", "Agua"),
            Phrase("Bread", "Pan"),
            Phrase("Coffee", "Café"),
            Phrase("The menu, please", "El menú, por favor"),
            Phrase("The bill, please", "La cuenta, por favor"),
            Phrase("It's delicious", "Está delicioso")
        ), 2),
        Lesson("es5", "es", "Family", "La Familia", listOf(
            Phrase("Family", "Familia"),
            Phrase("Mother", "Madre"),
            Phrase("Father", "Padre"),
            Phrase("Brother", "Hermano"),
            Phrase("Sister", "Hermana"),
            Phrase("Son", "Hijo"),
            Phrase("Daughter", "Hija"),
            Phrase("Grandmother", "Abuela"),
            Phrase("Grandfather", "Abuelo")
        ), 2),

        // ===== FRENCH (10 lessons) =====
        Lesson("fr1", "fr", "Basics 1", "Salutations", listOf(
            Phrase("Hello", "Bonjour"),
            Phrase("Good evening", "Bonsoir"),
            Phrase("Goodbye", "Au revoir"),
            Phrase("Thank you", "Merci"),
            Phrase("Please", "S'il vous plaît"),
            Phrase("Yes", "Oui"),
            Phrase("No", "Non"),
            Phrase("Excuse me", "Excusez-moi")
        ), 1),
        Lesson("fr2", "fr", "Basics 2", "Introductions", listOf(
            Phrase("My name is", "Je m'appelle"),
            Phrase("What is your name?", "Comment vous appelez-vous?"),
            Phrase("Pleased to meet you", "Enchanté"),
            Phrase("How are you?", "Comment allez-vous?"),
            Phrase("I am well", "Je vais bien"),
            Phrase("I am French", "Je suis français"),
            Phrase("Do you speak English?", "Parlez-vous anglais?"),
            Phrase("I don't understand", "Je ne comprends pas")
        ), 1),
        Lesson("fr3", "fr", "Numbers", "Les Nombres", listOf(
            Phrase("One", "Un"),
            Phrase("Two", "Deux"),
            Phrase("Three", "Trois"),
            Phrase("Four", "Quatre"),
            Phrase("Five", "Cinq"),
            Phrase("Six", "Six"),
            Phrase("Seven", "Sept"),
            Phrase("Eight", "Huit"),
            Phrase("Nine", "Neuf"),
            Phrase("Ten", "Dix")
        ), 1),
        Lesson("fr4", "fr", "At the Café", "Au Café", listOf(
            Phrase("Coffee", "Café"),
            Phrase("Tea", "Thé"),
            Phrase("Milk", "Lait"),
            Phrase("Sugar", "Sucre"),
            Phrase("Water", "Eau"),
            Phrase("Wine", "Vin"),
            Phrase("Bread", "Pain"),
            Phrase("Cheese", "Fromage"),
            Phrase("How much?", "Combien?")
        ), 2),

        // ===== MANDARIN (8 lessons) =====
        Lesson("zh1", "zh", "Basics 1", "问候 Greetings", listOf(
            Phrase("Hello", "你好"),
            Phrase("Good morning", "早上好"),
            Phrase("Thank you", "谢谢"),
            Phrase("You're welcome", "不客气"),
            Phrase("Goodbye", "再见"),
            Phrase("Sorry", "对不起"),
            Phrase("Yes", "是"),
            Phrase("No", "不是")
        ), 1),
        Lesson("zh2", "zh", "Basics 2", "自我介绍", listOf(
            Phrase("My name is", "我叫"),
            Phrase("What is your name?", "你叫什么名字?"),
            Phrase("How are you?", "你好吗?"),
            Phrase("I am fine", "我很好"),
            Phrase("Nice to meet you", "很高兴认识你"),
            Phrase("I am American", "我是美国人"),
            Phrase("I don't speak Chinese", "我不会说中文")
        ), 1),
        Lesson("zh3", "zh", "Numbers", "数字", listOf(
            Phrase("One", "一"),
            Phrase("Two", "二"),
            Phrase("Three", "三"),
            Phrase("Four", "四"),
            Phrase("Five", "五"),
            Phrase("Six", "六"),
            Phrase("Seven", "七"),
            Phrase("Eight", "八"),
            Phrase("Nine", "九"),
            Phrase("Ten", "十")
        ), 1),

        // ===== ENGLISH (for non-native speakers) =====
        Lesson("en1", "en", "Basics 1", "Essential Greetings", listOf(
            Phrase("Hello", "Hello"),
            Phrase("Good morning", "Good morning"),
            Phrase("Good afternoon", "Good afternoon"),
            Phrase("Good evening", "Good evening"),
            Phrase("How are you?", "How are you?"),
            Phrase("I'm fine, thank you", "I'm fine, thank you"),
            Phrase("Nice to meet you", "Nice to meet you"),
            Phrase("Goodbye", "Goodbye")
        ), 1),
        Lesson("en2", "en", "Numbers", "Counting 1-10", listOf(
            Phrase("One", "One"),
            Phrase("Two", "Two"),
            Phrase("Three", "Three"),
            Phrase("Four", "Four"),
            Phrase("Five", "Five"),
            Phrase("Six", "Six"),
            Phrase("Seven", "Seven"),
            Phrase("Eight", "Eight"),
            Phrase("Nine", "Nine"),
            Phrase("Ten", "Ten")
        ), 1),

        // ===== GERMAN (8 lessons) =====
        Lesson("de1", "de", "Basics 1", "Grüße", listOf(
            Phrase("Hello", "Hallo"),
            Phrase("Good morning", "Guten Morgen"),
            Phrase("Good evening", "Guten Abend"),
            Phrase("Goodbye", "Auf Wiedersehen"),
            Phrase("Thank you", "Danke"),
            Phrase("Please", "Bitte"),
            Phrase("Yes", "Ja"),
            Phrase("No", "Nein")
        ), 1),
        Lesson("de2", "de", "Introduction", "Vorstellung", listOf(
            Phrase("My name is", "Ich heiße"),
            Phrase("What is your name?", "Wie heißen Sie?"),
            Phrase("How are you?", "Wie geht es Ihnen?"),
            Phrase("I am well", "Mir geht es gut"),
            Phrase("Where are you from?", "Woher kommen Sie?"),
            Phrase("I am from", "Ich komme aus"),
            Phrase("Nice to meet you", "Schön, Sie kennenzulernen")
        ), 1),

        // ===== ITALIAN (8 lessons) =====
        Lesson("it1", "it", "Basics 1", "Saluti Essenziali", listOf(
            Phrase("Hello", "Ciao"),
            Phrase("Good morning", "Buongiorno"),
            Phrase("Good evening", "Buonasera"),
            Phrase("Good night", "Buonanotte"),
            Phrase("Goodbye", "Arrivederci"),
            Phrase("Please", "Per favore"),
            Phrase("Thank you", "Grazie"),
            Phrase("You're welcome", "Prego")
        ), 1),
        Lesson("it2", "it", "Introduction", "Presentazioni", listOf(
            Phrase("My name is", "Mi chiamo"),
            Phrase("What is your name?", "Come ti chiami?"),
            Phrase("How are you?", "Come stai?"),
            Phrase("I am well", "Sto bene"),
            Phrase("Nice to meet you", "Piacere di conoscerti"),
            Phrase("I am Italian", "Sono italiano"),
            Phrase("Where are you from?", "Da dove vieni?")
        ), 1),

        // ===== PORTUGUESE (8 lessons) =====
        Lesson("pt1", "pt", "Basics 1", "Saudações", listOf(
            Phrase("Hello", "Olá"),
            Phrase("Good morning", "Bom dia"),
            Phrase("Good afternoon", "Boa tarde"),
            Phrase("Good night", "Boa noite"),
            Phrase("How are you?", "Tudo bem?"),
            Phrase("I'm fine", "Estou bem"),
            Phrase("Thank you", "Obrigado"),
            Phrase("Goodbye", "Tchau")
        ), 1),
        Lesson("pt2", "pt", "Numbers", "Números", listOf(
            Phrase("One", "Um"),
            Phrase("Two", "Dois"),
            Phrase("Three", "Três"),
            Phrase("Four", "Quatro"),
            Phrase("Five", "Cinco"),
            Phrase("Six", "Seis"),
            Phrase("Seven", "Sete"),
            Phrase("Eight", "Oito"),
            Phrase("Nine", "Nove"),
            Phrase("Ten", "Dez")
        ), 1),

        // ===== RUSSIAN (6 lessons) =====
        Lesson("ru1", "ru", "Basics 1", "Приветствия", listOf(
            Phrase("Hello", "Привет"),
            Phrase("Good morning", "Доброе утро"),
            Phrase("Good evening", "Добрый вечер"),
            Phrase("Goodbye", "До свидания"),
            Phrase("Thank you", "Спасибо"),
            Phrase("Please", "Пожалуйста"),
            Phrase("Yes", "Да"),
            Phrase("No", "Нет")
        ), 1),
        Lesson("ru2", "ru", "Introduction", "Знакомство", listOf(
            Phrase("My name is", "Меня зовут"),
            Phrase("What is your name?", "Как вас зовут?"),
            Phrase("How are you?", "Как дела?"),
            Phrase("Nice to meet you", "Приятно познакомиться"),
            Phrase("I am Russian", "Я русский"),
            Phrase("I don't understand", "Я не понимаю")
        ), 1),

        // ===== JAPANESE (6 lessons) =====
        Lesson("jp1", "jp", "Basics 1", "あいさつ", listOf(
            Phrase("Hello", "こんにちは"),
            Phrase("Good morning", "おはよう"),
            Phrase("Good evening", "こんばんは"),
            Phrase("Goodbye", "さようなら"),
            Phrase("Thank you", "ありがとう"),
            Phrase("Excuse me", "すみません"),
            Phrase("Yes", "はい"),
            Phrase("No", "いいえ")
        ), 1),
        Lesson("jp2", "jp", "Introduction", "自己紹介", listOf(
            Phrase("My name is", "私の名前は"),
            Phrase("What is your name?", "お名前は何ですか?"),
            Phrase("Nice to meet you", "はじめまして"),
            Phrase("How are you?", "お元気ですか?"),
            Phrase("I am American", "私はアメリカ人です"),
            Phrase("I don't understand", "わかりません")
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
