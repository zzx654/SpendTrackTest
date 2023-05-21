package com.example.spendtrack.di

import com.example.spendtrack.data.SpendsDatabase
import com.example.spendtrack.data.SpendsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNoteRepository(db: SpendsDatabase): SpendsRepository {
        return SpendsRepository(db.spendDao)
    }
}