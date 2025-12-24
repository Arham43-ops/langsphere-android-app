package com.example.langsphere.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.langsphere.data.local.dao.UserDao
import com.example.langsphere.domain.model.User
import com.example.langsphere.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDao: UserDao
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    // Real stats from database
    val userEntity = authRepository.getCurrentUser().flatMapLatest { user ->
        if (user != null) {
            userDao.getUser(user.id)
        } else {
            flowOf(null)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect {
                _user.value = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun updateProfile(newName: String) {
        viewModelScope.launch {
            userEntity.value?.let { entity ->
                userDao.updateUser(entity.copy(name = newName))
            }
        }
    }
}
