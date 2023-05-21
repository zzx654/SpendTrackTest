package com.example.spendtrack.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SpendDao {

    @Insert
    suspend fun addSpend(spend: Spend)

    @Query("SELECT * FROM spends ORDER BY date DESC LIMIT 20")
    fun getLast20Spends(): Flow<List<Spend>>

}