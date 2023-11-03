package com.example.core.domain.domaincoin.repository

import com.example.core.domain.domaincoin.entity.Coin

interface CoinRepository {
    suspend fun getCoin(): Coin
}
