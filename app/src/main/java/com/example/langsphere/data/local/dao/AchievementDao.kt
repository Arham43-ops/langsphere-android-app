package com.example.langsphere.data.local.dao

import androidx.room.*
import com.example.langsphere.data.local.entity.AchievementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievements WHERE userId = :userId")
    fun getAchievements(userId: String): Flow<List<AchievementEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAchievements(achievements: List<AchievementEntity>)

    @Query("UPDATE achievements SET isUnlocked = 1 WHERE id = :achievementId AND userId = :userId")
    suspend fun unlockAchievement(userId: String, achievementId: String)
}
