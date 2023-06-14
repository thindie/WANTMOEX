package com.example.core.domain.domaincoinslist.repository

import com.example.core.domain.domaincoinslist.entity.Coin
import com.example.core.domain.domaincoinslist.entity.CoinsListSettings
import kotlinx.coroutines.flow.Flow

interface CoinsListRepository {
    fun observeCoins(settings: CoinsListSettings): Flow<List<Coin>>
    suspend fun actualizeObservingSource(): Boolean  // todo( segregate?
}
