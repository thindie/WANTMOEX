package com.example.core.database.instance

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Query("SELECT fromSymbol FROM coins")
    suspend fun getTickers(): List<String>

    @Query("SELECT * FROM coins LIMIT :capacity")
    fun observeAllCoins(
        capacity: Int,
    ): Flow<List<CoinDbModel>>

    @Query("SELECT * FROM coins WHERE fromSymbol == :ticker LIMIT 1")
    suspend fun getCoin(ticker: String): CoinDbModel

    @Upsert
    suspend fun upsertList(list: List<CoinDbModel>)

    @Upsert
    suspend fun upsertCoin(coin: CoinDbModel)
}
