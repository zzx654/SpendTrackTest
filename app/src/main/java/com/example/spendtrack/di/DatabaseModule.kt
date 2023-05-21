package com.example.spendtrack.di

import android.app.Application
import androidx.room.Room
import com.example.spendtrack.data.SpendsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideSpendsDatabase(app: Application): SpendsDatabase {
        return Room.databaseBuilder(
            app,
            SpendsDatabase::class.java,
            SpendsDatabase.DB_NAME
        ).build()
    }
}