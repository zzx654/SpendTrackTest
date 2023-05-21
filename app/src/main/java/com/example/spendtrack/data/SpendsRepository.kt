package com.example.spendtrack.data

import kotlinx.coroutines.flow.Flow

class SpendsRepository(
    private val dao: SpendDao
) {
    suspend fun addSpend(spend: Spend) = dao.addSpend(spend)

    fun getLast20Spends(): Flow<List<Spend>> = dao.getLast20Spends()
}