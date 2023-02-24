package com.example.thindie.wantmoex.data.storage

import com.example.thindie.wantmoex.data.storage.allCoins.CoinDBModel
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow

interface LocalCoinRepository {


    fun observeAllCoins(): Flow<Results<List<CoinDBModel>>>
    fun observeAllCoins(limit: Int): Flow<Results<List<CoinDBModel>>>
    fun observeCoin(fromSymbol: String): Flow<Results<CoinDBModel>>

    suspend fun addCoins(list: List<Coin>)
    suspend fun getCoin(fromSymbol: String): Results<CoinDBModel>
    suspend fun getAllCoins(): Results<List<CoinDBModel>>
    suspend fun getAllCoins(limit: Int): Results<List<CoinDBModel>>
}