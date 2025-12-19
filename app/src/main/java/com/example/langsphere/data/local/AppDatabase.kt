package com.example.langsphere.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.langsphere.data.local.dao.AchievementDao
import com.example.langsphere.data.local.dao.UserDao
import com.example.langsphere.data.local.entity.AchievementEntity
import com.example.langsphere.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, AchievementEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun achievementDao(): AchievementDao
}
