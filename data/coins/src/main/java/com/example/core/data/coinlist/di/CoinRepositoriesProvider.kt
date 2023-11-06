package com.example.core.data.coinlist.di

import com.example.core.network.apiservice.di.NetworkProvider
import com.example.thindie.domain.Coin
import com.example.thindie.domain.Repository

interface CoinRepositoriesProvider : NetworkProvider {
    fun provideCoinsRepository(): Repository<List<Coin>>
}
