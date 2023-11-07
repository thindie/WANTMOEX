package com.example.core.data.coinlist.di

import com.example.thindie.domain.Repository
import com.example.thindie.domain.entities.Coin

interface CoinRepositoriesProvider {
    fun provideCoinsRepository(): Repository<List<Coin>>
}
