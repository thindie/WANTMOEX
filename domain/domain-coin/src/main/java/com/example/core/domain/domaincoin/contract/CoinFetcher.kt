package com.example.core.domain.domaincoin.contract

import com.example.core.domain.domaincoin.entity.Coin

interface CoinFetcher {
    suspend operator fun invoke(): Coin
}
