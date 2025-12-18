package com.example.langsphere.di

import android.content.Context
import com.example.langsphere.data.local.AuthDataStore
import com.example.langsphere.data.repository.AuthRepositoryImpl
import com.example.langsphere.data.repository.LessonRepositoryImpl
import com.example.langsphere.domain.repository.AuthRepository
import com.example.langsphere.domain.repository.LessonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLessonRepository(): LessonRepository {
        return LessonRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAuthDataStore(@ApplicationContext context: Context): AuthDataStore {
        return AuthDataStore(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authDataStore: AuthDataStore): AuthRepository {
        return AuthRepositoryImpl(authDataStore)
    }
}
