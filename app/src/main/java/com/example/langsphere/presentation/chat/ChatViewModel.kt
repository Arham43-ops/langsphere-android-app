package com.example.langsphere.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(UUID.randomUUID().toString(), "Hello! I am your AI Language Tutor. \uD83E\uDD16\nWhich language are you practicing today?", false)
        )
    )
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _isTyping = MutableStateFlow(false)
    val isTyping: StateFlow<Boolean> = _isTyping.asStateFlow()

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        val userMsg = ChatMessage(UUID.randomUUID().toString(), text, true)
        _messages.value = _messages.value + userMsg

        simulateAIResponse(text)
    }

    private fun simulateAIResponse(userText: String) {
        viewModelScope.launch {
            _isTyping.value = true
            delay(1500) // Simulate network/thinking delay

            val aiText = when {
                userText.contains("spanish", true) -> "Excelente! \uD83C\uDDEA\uD83C\uDDF8 Spanish is a beautiful language. Try saying 'Hola, ¿cómo estás?'"
                userText.contains("french", true) -> "Très bien! \uD83C\uDDEB\uD83C\uDDF7 Let's start with 'Bonjour'. Can you use it in a sentence?"
                userText.contains("hello", true) || userText.contains("hi", true) -> "Hi there! Ready to learn? Ask me how to say something in another language."
                userText.length < 5 -> "Could you say a bit more? I want to help you practice effectively!"
                else -> "That's interesting! In many languages, context is key. Try translating that phrase for me: '$userText'"
            }

            val aiMsg = ChatMessage(UUID.randomUUID().toString(), aiText, false)
            _messages.value = _messages.value + aiMsg
            _isTyping.value = false
        }
    }
}
