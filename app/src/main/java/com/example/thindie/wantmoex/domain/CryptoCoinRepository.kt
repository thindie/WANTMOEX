package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow


interface CryptoCoinRepository {
    suspend fun getAllCoins(): Flow<List<Coin>?>
    suspend fun getCoin(fromSymbol: String): Coin?
}

