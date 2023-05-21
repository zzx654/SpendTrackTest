package com.example.spendtrack.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Spend::class], version =1)
@TypeConverters(DateConverter::class)
abstract class SpendsDatabase : RoomDatabase() {

    abstract val spendDao: SpendDao

    companion object {
        const val DB_NAME = "Spends_Database.db"

    }
}