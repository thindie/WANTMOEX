package com.example.thindie.wantmoex.data.storage.allCoins

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Query("SELECT * FROM coinsTable  ORDER BY lastUpdate DESC")
    suspend fun getAllCoins(): List<CoinDBModel>

    @Query("SELECT * FROM coinsTable  ORDER BY lastUpdate DESC")
    fun observeAllCoins(): Flow<List<CoinDBModel>>

    @Query("SELECT * FROM coinsTable WHERE fromSymbol == :fsym LIMIT 1")
    suspend fun getCoin(fsym: String): CoinDBModel

    @Query("SELECT * FROM coinsTable WHERE fromSymbol == :fsym LIMIT 1")
    fun observeCoin(fsym: String): Flow<CoinDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(coinList: List<CoinDBModel>)
}