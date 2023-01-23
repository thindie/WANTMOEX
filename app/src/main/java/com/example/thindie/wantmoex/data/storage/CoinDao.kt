package com.example.thindie.wantmoex.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDao {

    @Query("SELECT * FROM coinsTable  ORDER BY lastUpdate DESC")
    suspend fun getAllCoins(): List<CoinDBModel>

    @Query("SELECT * FROM coinsTable WHERE fromSymbol == :fsym LIMIT 1")
    suspend fun getCoin(fsym: String): CoinDBModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(coinList: List<CoinDBModel>)
}