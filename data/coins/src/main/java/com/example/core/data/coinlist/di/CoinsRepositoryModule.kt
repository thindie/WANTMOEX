package com.example.core.data.coinlist.di

import com.example.core.data.coinlist.repositoryimpl.CoinsRepositoryImpl
import com.example.core.network.apiservice.ApiService
import com.example.thindie.domain.Coin
import com.example.thindie.domain.Repository
import dagger.Module
import dagger.Provides

@Module
internal class CoinsRepositoryModule {
    @Provides
    fun bindCoinsRepository(service: ApiService): Repository<List<Coin>> {
        return CoinsRepositoryImpl(service)
    }
}
