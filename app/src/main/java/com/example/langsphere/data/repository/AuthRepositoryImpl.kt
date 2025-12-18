package com.example.langsphere.data.repository

import com.example.langsphere.data.local.AuthDataStore
import com.example.langsphere.domain.model.User
import com.example.langsphere.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataStore: AuthDataStore
) : AuthRepository {

    override fun isUserLoggedIn(): Flow<Boolean> {
        return authDataStore.accessToken.map { it != null }
    }

    override fun getCurrentUser(): Flow<User?> {
        return combine(authDataStore.userName, authDataStore.userEmail) { name, email ->
            if (name != null && email != null) {
                User(id = "local_id", name = name, email = email)
            } else {
                null
            }
        }
    }

    override suspend fun login(email: String, password: String): Result<User> {
        delay(1000) // Simulate network
        return if (email.isNotEmpty() && password.length >= 6) {
            val user = User(id = "123", name = "Test User", email = email)
            authDataStore.saveAuthToken("fake_token_123", user.name, user.email)
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun register(name: String, email: String, password: String): Result<User> {
        delay(1000)
        return if (email.isNotEmpty() && password.length >= 6) {
            val user = User(id = "124", name = name, email = email)
            authDataStore.saveAuthToken("fake_token_124", user.name, user.email)
            Result.success(user)
        } else {
            Result.failure(Exception("Registration failed"))
        }
    }

    override suspend fun logout() {
        authDataStore.clearAuth()
    }
}
