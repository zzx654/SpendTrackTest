package com.example.spendtrack

import android.app.Application
import androidx.room.Room
import com.example.spendtrack.data.SpendsDatabase
import com.example.spendtrack.data.SpendsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideSpendDatabase(app: Application): SpendsDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            SpendsDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: SpendsDatabase): SpendsRepository {
        return SpendsRepository(db.spendDao)
    }
}