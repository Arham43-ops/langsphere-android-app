package com.example.langsphere.presentation.lesson

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.langsphere.domain.model.Lesson
import com.example.langsphere.domain.repository.LessonRepository
import com.example.langsphere.presentation.util.SpeechRecognitionManager
import com.example.langsphere.presentation.util.SpeechResult
import com.example.langsphere.presentation.util.TextToSpeechManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val repository: LessonRepository,
    private val ttsManager: TextToSpeechManager,
    private val speechManager: SpeechRecognitionManager,
    private val userDao: com.example.langsphere.data.local.dao.UserDao,
    private val authRepository: com.example.langsphere.domain.repository.AuthRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val lessonId: String = checkNotNull(savedStateHandle["lessonId"])

    private val _lessonState = MutableStateFlow<LessonUiState>(LessonUiState.Loading)
    val lessonState: StateFlow<LessonUiState> = _lessonState.asStateFlow()

    private val _speechState = MutableStateFlow<SpeechResult?>(null)
    val speechState: StateFlow<SpeechResult?> = _speechState.asStateFlow()

    init {
        loadLesson()
    }

    private fun loadLesson() {
        viewModelScope.launch {
            val lesson = repository.getLessonDetails(lessonId)
            if (lesson != null) {
                _lessonState.value = LessonUiState.Success(lesson)
            } else {
                _lessonState.value = LessonUiState.Error("Lesson not found")
            }
        }
    }

    fun completeLesson() {
        viewModelScope.launch {
            val lesson = (_lessonState.value as? LessonUiState.Success)?.lesson ?: return@launch
            val xpReward = lesson.difficultyLevel * 20 // 20 XP per difficulty level
            
            // Award XP to current user
            authRepository.getCurrentUser().collect { user ->
                if (user != null) {
                    userDao.addXp(user.id, xpReward)
                }
            }
        }
    }

    fun playAudio(text: String, languageCode: String) {
        val locale = when (languageCode) {
            "es" -> Locale("es", "ES")      // Spanish (Spain)
            "fr" -> Locale.FRANCE            // French
            "de" -> Locale.GERMANY           // German
            "it" -> Locale.ITALY             // Italian
            "pt" -> Locale("pt", "BR")       // Portuguese (Brazil)
            "ru" -> Locale("ru", "RU")       // Russian
            "zh" -> Locale.SIMPLIFIED_CHINESE // Mandarin
            "jp" -> Locale.JAPAN             // Japanese
            "en" -> Locale.US                // English
            else -> Locale.US
        }
        ttsManager.speak(text, locale)
    }

    fun startListening(languageCode: String) {
        viewModelScope.launch {
            speechManager.startListening(languageCode).collect { result ->
                _speechState.value = result
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
    }
}

sealed class LessonUiState {
    object Loading : LessonUiState()
    data class Success(val lesson: Lesson) : LessonUiState()
    data class Error(val message: String) : LessonUiState()
}
