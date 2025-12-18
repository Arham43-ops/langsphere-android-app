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
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val languageId: String = checkNotNull(savedStateHandle["languageId"])

    private val _lessonState = MutableStateFlow<LessonUiState>(LessonUiState.Loading)
    val lessonState: StateFlow<LessonUiState> = _lessonState.asStateFlow()

    private val _speechState = MutableStateFlow<SpeechResult?>(null)
    val speechState: StateFlow<SpeechResult?> = _speechState.asStateFlow()

    init {
        loadLesson()
    }

    private fun loadLesson() {
        viewModelScope.launch {
            repository.getLessonsForLanguage(languageId).collect { lessons ->
                if (lessons.isNotEmpty()) {
                    _lessonState.value = LessonUiState.Success(lessons.first())
                } else {
                    _lessonState.value = LessonUiState.Error("No lessons found for this language")
                }
            }
        }
    }

    fun playAudio(text: String, languageCode: String) {
        val locale = when (languageCode) {
            "es" -> Locale("es", "ES")
            "fr" -> Locale.FRANCE
            "de" -> Locale.GERMANY
            "jp" -> Locale.JAPAN
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
