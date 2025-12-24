package com.example.langsphere.presentation.lesson

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.langsphere.domain.model.Lesson
import com.example.langsphere.domain.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonListViewModel @Inject constructor(
    private val repository: LessonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val languageId: String = checkNotNull(savedStateHandle["languageId"])

    private val _lessons = MutableStateFlow<List<Lesson>>(emptyList())
    val lessons: StateFlow<List<Lesson>> = _lessons.asStateFlow()

    init {
        loadLessons()
    }

    private fun loadLessons() {
        viewModelScope.launch {
            repository.getLessonsForLanguage(languageId).collect { lessonList ->
                _lessons.value = lessonList.sortedBy { it.difficultyLevel }
            }
        }
    }
}
