package com.example.core.domain.domaincoin.di

import com.example.core.domain.domaincoin.contract.CoinFetcher
import com.example.core.domain.domaincoin.usecase.GetCoinUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinFetcherModule {
    @Binds
    fun bindCoinUseCase(useCase: GetCoinUseCase): CoinFetcher
}
