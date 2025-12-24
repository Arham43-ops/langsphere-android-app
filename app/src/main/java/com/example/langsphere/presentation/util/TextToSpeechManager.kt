package com.example.langsphere.presentation.util

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TextToSpeechManager @Inject constructor(
    @ApplicationContext context: Context
) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isInitialized = false
    private var isSpeaking = false
    private val pendingRequests = mutableListOf<Pair<String, Locale>>()

    init {
        tts = TextToSpeech(context, this)
        setupUtteranceListener()
    }
    
    private fun setupUtteranceListener() {
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                isSpeaking = true
                Log.d("TTS", "🎙️ Started speaking")
            }

            override fun onDone(utteranceId: String?) {
                isSpeaking = false
                Log.d("TTS", "✅ Finished speaking")
            }

            override fun onError(utteranceId: String?) {
                isSpeaking = false
                Log.e("TTS", "❌ Error during speech")
            }
        })
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            Log.d("TTS", "✅ TTS initialized successfully")
            
            // Process any pending requests
            pendingRequests.forEach { (text, locale) ->
                speakNow(text, locale)
            }
            pendingRequests.clear()
        } else {
            Log.e("TTS", "❌ TTS initialization failed with status: $status")
        }
    }

    fun speak(text: String, locale: Locale = Locale.US) {
        Log.d("TTS", "📢 Speak requested: '$text' in $locale")
        
        // Stop current speech if speaking
        if (isSpeaking) {
            Log.d("TTS", "⏹️ Stopping current speech")
            tts?.stop()
        }
        
        if (isInitialized) {
            speakNow(text, locale)
        } else {
            Log.d("TTS", "⏳ TTS not ready, queuing request")
            pendingRequests.add(Pair(text, locale))
        }
    }

    private fun speakNow(text: String, locale: Locale) {
        tts?.let { textToSpeech ->
            val langResult = textToSpeech.setLanguage(locale)
            
            when (langResult) {
                TextToSpeech.LANG_MISSING_DATA -> {
                    Log.e("TTS", "❌ Language data missing for: $locale")
                }
                TextToSpeech.LANG_NOT_SUPPORTED -> {
                    Log.e("TTS", "❌ Language not supported: $locale, falling back to US")
                    textToSpeech.setLanguage(Locale.US)
                }
                else -> {
                    Log.d("TTS", "✅ Language set to: $locale")
                }
            }
            
            val speakResult = textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "utteranceId")
            
            if (speakResult == TextToSpeech.SUCCESS) {
                Log.d("TTS", "🔊 Speaking: '$text'")
            } else {
                Log.e("TTS", "❌ Failed to speak, error code: $speakResult")
            }
        } ?: run {
            Log.e("TTS", "❌ TTS instance is null!")
        }
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        isInitialized = false
        isSpeaking = false
        pendingRequests.clear()
        Log.d("TTS", "🛑 TTS shutdown")
    }
}
