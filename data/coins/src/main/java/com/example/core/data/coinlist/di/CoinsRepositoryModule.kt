package com.example.core.data.coinlist.di

import com.example.core.data.coinlist.repositoryimpl.CoinsRepositoryImpl
import com.example.core.network.apiservice.ApiService
import com.example.thindie.domain.Repository
import com.example.thindie.domain.entities.Coin
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class CoinsRepositoryModule {
    @Provides
    @Singleton
    fun bindCoinsRepository(service: ApiService): Repository<List<Coin>> {
        return CoinsRepositoryImpl(service)
    }
}
