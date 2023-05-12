package com.example.thindie.wantmoex.data.network

import com.example.core.network.dto.CoinDTO
import com.example.thindie.wantmoex.domain.Results
import kotlinx.coroutines.flow.Flow

interface RemoteCoinRepository {
    fun observeAllCoins(): Flow<Results<List<com.example.core.network.dto.CoinDTO>>>
    fun observeAllCoins(limit: Int): Flow<Results<List<com.example.core.network.dto.CoinDTO>>>
    fun observeCoin(fromSymbol: String): Flow<Results<com.example.core.network.dto.CoinDTO>>
    suspend fun getCoin(fromSymbol: String): Results<com.example.core.network.dto.CoinDTO>
    suspend fun getAllCoins(): Results<List<com.example.core.network.dto.CoinDTO>>
    suspend fun getAllCoins(limit: Int): Results<List<com.example.core.network.dto.CoinDTO>>
}
