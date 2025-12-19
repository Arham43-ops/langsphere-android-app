package com.example.langsphere.data.local.dao

import androidx.room.*
import com.example.langsphere.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("UPDATE users SET totalXp = totalXp + :xp WHERE id = :userId")
    suspend fun addXp(userId: String, xp: Int)

    // For Leaderboard
    @Query("SELECT * FROM users ORDER BY totalXp DESC LIMIT 50")
    fun getTopUsers(): Flow<List<UserEntity>>
}
