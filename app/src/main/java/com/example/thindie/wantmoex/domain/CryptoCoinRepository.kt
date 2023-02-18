package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow


interface CryptoCoinRepository {
    fun observeAllCoins(limit: Int): Flow<Results<List<Coin>>>
    fun observeCoin(fromSymbol: String): Flow<Results<Coin>>
    suspend fun getCoin(fromSymbol: String): Results<Coin>
    suspend fun getAllCoins(limit: Int): Results<List<Coin>>

}

