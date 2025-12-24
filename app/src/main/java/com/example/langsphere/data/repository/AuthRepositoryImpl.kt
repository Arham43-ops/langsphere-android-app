package com.example.langsphere.data.repository

import com.example.langsphere.data.local.AuthDataStore
import com.example.langsphere.data.local.dao.UserDao
import com.example.langsphere.data.local.entity.UserEntity
import com.example.langsphere.domain.model.User
import com.example.langsphere.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authDataStore: AuthDataStore,
    private val userDao: UserDao
) : AuthRepository {

    override fun isUserLoggedIn(): Flow<Boolean> {
        return authDataStore.accessToken.map { it != null }
    }

    override fun getCurrentUser(): Flow<User?> {
        return authDataStore.accessToken.flatMapLatest { userId ->
             if (userId == null) {
                 flowOf(null)
             } else {
                 userDao.getUser(userId).map { entity ->
                     entity?.let { User(it.id, it.name, it.email) }
                 }
             }
        }
    }

    override suspend fun login(email: String, pass: String): Result<User> {
        delay(1000) // Sim network
        val userEntity = userDao.getUserByEmail(email)
        if (userEntity != null) {
            authDataStore.saveAuthToken(userEntity.id, userEntity.name, userEntity.email)
            return Result.success(User(userEntity.id, userEntity.name, userEntity.email))
        }
        return Result.failure(Exception("User not found"))
    }

    override suspend fun register(name: String, email: String, pass: String): Result<User> {
        delay(1000)
        if (userDao.getUserByEmail(email) != null) {
             return Result.failure(Exception("User already exists"))
        }
        val newUser = UserEntity(
            id = email, // Using email as ID for simplicity
            name = name,
            email = email,
            totalXp = 0 // Users earn XP by completing lessons
        )
        userDao.insertUser(newUser)
        authDataStore.saveAuthToken(newUser.id, newUser.name, newUser.email)
        return Result.success(User(newUser.id, newUser.name, newUser.email))
    }

    override suspend fun logout() {
        authDataStore.clearAuth()
    }
}
