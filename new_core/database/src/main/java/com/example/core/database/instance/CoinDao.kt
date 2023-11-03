package com.example.core.database.instance

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Query("SELECT fromSymbol FROM coins")
    fun getTickers(): List<String>

    @Query("SELECT * FROM coins LIMIT :capacity")
    fun observeAllCoins(
        capacity: Int,
    ): Flow<List<CoinDbModel>>

    @Query("SELECT * FROM coins WHERE fromSymbol == :ticker LIMIT 1")
    fun getCoin(ticker: String): CoinDbModel

    @Upsert
    fun upsertList(list: List<CoinDbModel>)

    @Upsert
    fun upsertCoin(coin: CoinDbModel)
}
