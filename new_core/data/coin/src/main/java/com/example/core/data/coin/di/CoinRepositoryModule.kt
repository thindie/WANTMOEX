package com.example.core.data.coin.di

import com.example.core.data.coin.repositoryimpl.CoinRepositoryImpl
import com.example.core.domain.domaincoin.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinRepositoryModule {
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository
}
