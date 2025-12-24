package com.example.langsphere.presentation.util

/**
 * Pronunciation Analyzer
 * Provides feedback on pronunciation accuracy and suggestions for improvement
 */
class PronunciationAnalyzer {
    
    data class PronunciationResult(
        val accuracy: Int, // 0-100
        val feedback: String,
        val level: FeedbackLevel,
        val suggestions: List<String>
    )
    
    enum class FeedbackLevel {
        EXCELLENT, GOOD, FAIR, NEEDS_IMPROVEMENT
    }
    
    /**
     * Analyze pronunciation based on speech recognition confidence
     * In a real implementation, this would use advanced phonetic analysis
     */
    fun analyzePronunciation(
        spokenText: String,
        targetText: String,
        confidence: Float = 0.8f // Speech recognition confidence score
    ): PronunciationResult {
        
        // Calculate similarity between spoken and target text
        val similarity = calculateSimilarity(spokenText.lowercase(), targetText.lowercase())
        
        // Combine confidence and similarity for accuracy score
        val accuracy = ((confidence * 0.6f + similarity * 0.4f) * 100).toInt().coerceIn(0, 100)
        
        // Determine feedback level
        val level = when {
            accuracy >= 90 -> FeedbackLevel.EXCELLENT
            accuracy >= 75 -> FeedbackLevel.GOOD
            accuracy >= 60 -> FeedbackLevel.FAIR
            else -> FeedbackLevel.NEEDS_IMPROVEMENT
        }
        
        // Generate feedback message
        val feedback = generateFeedback(accuracy, level, spokenText, targetText)
        
        // Generate improvement suggestions
        val suggestions = generateSuggestions(level, spokenText, targetText)
        
        return PronunciationResult(accuracy, feedback, level, suggestions)
    }
    
    private fun calculateSimilarity(text1: String, text2: String): Float {
        if (text1 == text2) return 1.0f
        
        // Simple Levenshtein distance for similarity
        val maxLen = maxOf(text1.length, text2.length)
        if (maxLen == 0) return 1.0f
        
        val distance = levenshteinDistance(text1, text2)
        return 1 - (distance.toFloat() / maxLen)
    }
    
    private fun levenshteinDistance(s1: String, s2: String): Int {
        val len1 = s1.length
        val len2 = s2.length
        val matrix = Array(len1 + 1) { IntArray(len2 + 1) }
        
        for (i in 0..len1) matrix[i][0] = i
        for (j in 0..len2) matrix[0][j] = j
        
        for (i in 1..len1) {
            for (j in 1..len2) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                matrix[i][j] = minOf(
                    matrix[i - 1][j] + 1,      // deletion
                    matrix[i][j - 1] + 1,      // insertion
                    matrix[i - 1][j - 1] + cost // substitution
                )
            }
        }
        
        return matrix[len1][len2]
    }
    
    private fun generateFeedback(
        accuracy: Int,
        level: FeedbackLevel,
        spoken: String,
        target: String
    ): String {
        return when (level) {
            FeedbackLevel.EXCELLENT -> {
                val messages = listOf(
                    "🌟 Excellent pronunciation! You nailed it!",
                    "🎉 Perfect! Your pronunciation is spot-on!",
                    "✨ Outstanding! You're speaking like a native!",
                    "🏆 Superb pronunciation! Keep it up!"
                )
                messages.random()
            }
            FeedbackLevel.GOOD -> {
                val messages = listOf(
                    "👍 Great job! Your pronunciation is very good!",
                    "😊 Well done! Just minor improvements needed.",
                    "⭐ Good work! You're getting it right!",
                    "💪 Nice! Your pronunciation is improving!"
                )
                messages.random()
            }
            FeedbackLevel.FAIR -> {
                "🎯 Not bad! Keep practicing to improve clarity."
            }
            FeedbackLevel.NEEDS_IMPROVEMENT -> {
                "📚 Keep practicing! Listen carefully and try again."
            }
        }
    }
    
    private fun generateSuggestions(
        level: FeedbackLevel,
        spoken: String,
        target: String
    ): List<String> {
        val suggestions = mutableListOf<String>()
        
        when (level) {
            FeedbackLevel.EXCELLENT -> {
                suggestions.add("✓ Perfect! Try more challenging phrases")
            }
            FeedbackLevel.GOOD -> {
                suggestions.add("• Listen to the audio again for subtle nuances")
                suggestions.add("• Pay attention to stress and intonation")
            }
            FeedbackLevel.FAIR -> {
                suggestions.add("• Speak slower and enunciate clearly")
                suggestions.add("• Listen to the native pronunciation multiple times")
                suggestions.add("• Break the phrase into smaller parts")
            }
            FeedbackLevel.NEEDS_IMPROVEMENT -> {
                suggestions.add("• Listen carefully to the native pronunciation")
                suggestions.add("• Try repeating word by word")
                suggestions.add("• Record yourself and compare with the audio")
                suggestions.add("• Practice in a quiet environment")
            }
        }
        
        return suggestions
    }
    
    /**
     * Get emoji for visual feedback
     */
    fun getFeedbackEmoji(level: FeedbackLevel): String {
        return when (level) {
            FeedbackLevel.EXCELLENT -> "🌟"
            FeedbackLevel.GOOD -> "👍"
            FeedbackLevel.FAIR -> "🎯"
            FeedbackLevel.NEEDS_IMPROVEMENT -> "📚"
        }
    }
    
    /**
     * Get color for visual feedback (as hex)
     */
    fun getFeedbackColor(level: FeedbackLevel): Long {
        return when (level) {
            FeedbackLevel.EXCELLENT -> 0xFF4CAF50 // Green
            FeedbackLevel.GOOD -> 0xFF8BC34A // Light Green
            FeedbackLevel.FAIR -> 0xFFFFC107 // Amber
            FeedbackLevel.NEEDS_IMPROVEMENT -> 0xFFFF5722 // Deep Orange
        }
    }
}
