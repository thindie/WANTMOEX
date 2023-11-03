package com.example.core.domain.domaincoinslist.di

import com.example.core.domain.domaincoinslist.contract.CoinsListFetcher
import com.example.core.domain.domaincoinslist.usecase.GetCoinsUseCaseList
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinsListFetcherModule {
    @Binds
    fun bindCoinsListUseCase(useCase: GetCoinsUseCaseList): CoinsListFetcher
}
