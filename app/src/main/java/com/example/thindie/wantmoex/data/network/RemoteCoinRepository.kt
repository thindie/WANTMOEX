package com.example.thindie.wantmoex.data.network

import com.example.thindie.wantmoex.data.network.dto.CoinDTO
import com.example.thindie.wantmoex.domain.Results
import kotlinx.coroutines.flow.Flow


interface RemoteCoinRepository {
    fun observeAllCoins(): Flow<Results<List<CoinDTO>>>
    fun observeAllCoins(limit: Int): Flow<Results<List<CoinDTO>>>
    fun observeCoin(fromSymbol: String): Flow<Results<CoinDTO>>
    suspend fun getCoin(fromSymbol: String): Results<CoinDTO>?
    suspend fun getAllCoins(): Results<List<CoinDTO>>
    suspend fun getAllCoins(limit: Int): Results<List<CoinDTO>>

}

