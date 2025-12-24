package com.example.langsphere.presentation.util

import kotlin.random.Random

/**
 * Smart Conversation Engine
 * Provides context-aware, language-specific responses for chat interactions
 */
class ConversationEngine {
    
    private val conversationHistory = mutableListOf<Pair<String, String>>() // user message, bot response
    
    /**
     * Generate intelligent response based on user input and selected language
     */
    fun generateResponse(userMessage: String, languageCode: String = "en"): String {
        val lowerMessage = userMessage.lowercase().trim()
        
        // Language-specific greetings
        if (isGreeting(lowerMessage)) {
            return getGreetingResponse(languageCode)
        }
        
        // Learning motivation
        if (isMotivationRequest(lowerMessage)) {
            return getMotivationResponse(languageCode)
        }
        
        // Translation requests
        if (isTranslationRequest(lowerMessage)) {
            return getTranslationHelp(lowerMessage, languageCode)
        }
        
        // Grammar help
        if (isGrammarQuestion(lowerMessage)) {
            return getGrammarHelp(lowerMessage, languageCode)
        }
        
        // Practice suggestions
        if (isPracticeRequest(lowerMessage)) {
            return getPracticeAdvice(languageCode)
        }
        
        // Learning tips
        if (isLearningTipsRequest(lowerMessage)) {
            return getLearningTips(languageCode)
        }
        
        // Default contextual response
        return getContextualResponse(lowerMessage, languageCode)
    }
    
    private fun isGreeting(message: String): Boolean {
        val greetingPatterns = listOf("hello", "hi", "hey", "hola", "bonjour", "salut", "ciao")
        return greetingPatterns.any { message.contains(it) }
    }
    
    private fun getGreetingResponse(lang: String): String {
        val responses = when (lang) {
            "es" -> listOf(
                "¡Hola! 👋 How can I help you learn Spanish today?",
                "¡Bienvenido! Ready to practice some Spanish?",
                "¡Hola amigo! Let's learn something new today!"
            )
            "fr" -> listOf(
                "Bonjour! 👋 Ready to practice French?",
                "Salut! How can I assist your French learning?",
                "Bienvenue! Let's make progress in French today!"
            )
            "de" -> listOf(
                "Guten Tag! Ready to learn German?",
                "Hallo! Let's practice some German together!",
                "Willkommen! How can I help with German?"
            )
            else -> listOf(
                "Hello! 👋 How can I help you learn today?",
                "Hi there! Ready to practice?",
                "Welcome! Let's start learning!"
            )
        }
        return responses.random()
    }
    
    private fun isMotivationRequest(message: String): Boolean {
        val patterns = listOf("motivate", "encourage", "hard", "difficult", "give up", "tired")
        return patterns.any { message.contains(it) }
    }
    
    private fun getMotivationResponse(lang: String): String {
        val responses = listOf(
            "You're doing great! 💪 Every lesson brings you closer to fluency.",
            "Remember: consistency beats perfection! Keep practicing daily. 🌟",
            "Learning a language is a journey, not a race. You've got this! 🚀",
            "Each word you learn is a victory! Celebrate your progress. 🎉",
            "Don't give up! Even 10 minutes a day makes a huge difference. ⭐"
        )
        return responses.random()
    }
    
    private fun isTranslationRequest(message: String): Boolean {
        val patterns = listOf("how do you say", "translate", "what is", "mean in", "word for")
        return patterns.any { message.contains(it) }
    }
    
    private fun getTranslationHelp(message: String, lang: String): String {
        return when {
            message.contains("hello") || message.contains("hi") -> {
                when (lang) {
                    "es" -> "In Spanish, 'Hello' is 'Hola' 👋"
                    "fr" -> "In French, 'Hello' is 'Bonjour' or 'Salut' (informal) 👋"
                    "de" -> "In German, 'Hello' is 'Hallo' or 'Guten Tag' 👋"
                    else -> "Try checking the vocabulary section for more translations! 📚"
                }
            }
            message.contains("thank") -> {
                when (lang) {
                    "es" -> "In Spanish, 'Thank you' is 'Gracias' 🙏"
                    "fr" -> "In French, 'Thank you' is 'Merci' 🙏"
                    "de" -> "In German, 'Thank you' is 'Danke' 🙏"
                    else -> "Check your lessons for common phrases! 📖"
                }
            }
            else -> "I can help with common phrases! Try asking about specific words or check the vocabulary section. 📚"
        }
    }
    
    private fun isGrammarQuestion(message: String): Boolean {
        val patterns = listOf("grammar", "conjugate", "tense", "verb", "plural", "gender")
        return patterns.any { message.contains(it) }
    }
    
    private fun getGrammarHelp(message: String, lang: String): String {
        return when (lang) {
            "es" -> "Spanish grammar tip: Remember that nouns have gender (el/la). Practice with your lessons! 📖"
            "fr" -> "French grammar tip: French has masculine (le) and feminine (la) nouns. Keep practicing! 📚"
            "de" -> "German grammar tip: German has three genders: der (masculine), die (feminine), das (neuter). 📖"
            else -> "Grammar is best learned through practice! Complete more lessons to master the patterns. 🎯"
        }
    }
    
    private fun isPracticeRequest(message: String): Boolean {
        val patterns = listOf("practice", "exercise", "drill", "what should i", "how can i improve")
        return patterns.any { message.contains(it) }
    }
    
    private fun getPracticeAdvice(lang: String): String {
        val advice = listOf(
            "Try completing a lesson to practice vocabulary and pronunciation! 📚",
            "Use the speech recognition feature to practice your pronunciation! 🎤",
            "Review your vocabulary list and try using words in sentences! ✍️",
            "Practice makes perfect! Try to complete at least one lesson daily. 🗓️",
            "Mix it up: alternate between lessons, vocabulary, and conversation practice! 🔄"
        )
        return advice.random()
    }
    
    private fun isLearningTipsRequest(message: String): Boolean {
        val patterns = listOf("tips", "advice", "help me learn", "how to", "best way")
        return patterns.any { message.contains(it) }
    }
    
    private fun getLearningTips(lang: String): String {
        val tips = listOf(
            "💡 Tip: Practice every day, even if just for 10 minutes. Consistency is key!",
            "💡 Tip: Don't just memorize - try to use new words in sentences!",
            "💡 Tip: Listen to music or watch shows in your target language for immersion!",
            "💡 Tip: Make flashcards for difficult words and review them regularly!",
            "💡 Tip: Practice speaking out loud, even if you're alone. Build that confidence!",
            "💡 Tip: Set small, achievable goals and celebrate when you reach them!"
        )
        return tips.random()
    }
    
    private fun getContextualResponse(message: String, lang: String): String {
        // Detect question patterns
        if (message.contains("?") || message.startsWith("what") || message.startsWith("how") || 
            message.startsWith("why") || message.startsWith("where") || message.startsWith("when")) {
            return getQuestionResponse(lang)
        }
        
        // Enthusiasm responses
        if (message.contains("!") || message.contains("love") || message.contains("great") || message.contains("awesome")) {
            return getEnthusiasticResponse()
        }
        
        // Default helpful response
        return getDefaultResponse(lang)
    }
    
    private fun getQuestionResponse(lang: String): String {
        val responses = listOf(
            "That's a great question! Try completing relevant lessons to learn more. 📚",
            "I can help with that! Check the lesson topics for detailed explanations. 🎓",
            "Interesting question! Practice with the lessons to discover the answer. 🔍",
            "Good thinking! The lessons cover this topic in detail. Keep learning! 📖"
        )
        return responses.random()
    }
    
    private fun getEnthusiasticResponse(): String {
        val responses = listOf(
            "That's the spirit! Keep up the great work! 🌟",
            "Your enthusiasm is amazing! You'll master this in no time! 🚀",
            "Love that energy! Keep practicing and you'll see great results! ⭐",
            "Awesome attitude! That's the key to successful language learning! 💪"
        )
        return responses.random()
    }
    
    private fun getDefaultResponse(lang: String): String {
        val responses = listOf(
            "I'm here to help you learn! Try asking about grammar, vocabulary, or practice tips. 💬",
            "Let's make progress together! What would you like to practice today? 🎯",
            "I can assist with translations, grammar tips, or practice suggestions! How can I help? 🤝",
            "Keep learning! Feel free to ask me about any language topics. 📚"
        )
        return responses.random()
    }
    
    fun clearHistory() {
        conversationHistory.clear()
    }
}
