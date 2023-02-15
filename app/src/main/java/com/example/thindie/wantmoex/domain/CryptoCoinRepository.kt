package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow


interface CryptoCoinRepository {
    fun observeAllCoins(): Flow<Result<List<Coin>>>
    fun observeAllCoins(limit: Int): Flow<Result<List<Coin>>>
    fun observeCoin(fromSymbol: String): Flow<Result<Coin>>

    suspend fun getCoin(fromSymbol: String): Result<Coin>

    suspend fun getAllCoins(): Result<List<Coin>>
    suspend fun getAllCoins(limit: Int): Result<List<Coin>>

}

