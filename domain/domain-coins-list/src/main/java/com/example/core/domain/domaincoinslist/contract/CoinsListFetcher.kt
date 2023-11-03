package com.example.core.domain.domaincoinslist.contract

import com.example.core.domain.domaincoinslist.entity.Coin
import com.example.core.domain.domaincoinslist.entity.CoinsListSettings
import kotlinx.coroutines.flow.Flow

interface CoinsListFetcher {
    suspend fun fetchCoinsListDependOn(settings: CoinsListSettings): Flow<List<Coin>>
}
