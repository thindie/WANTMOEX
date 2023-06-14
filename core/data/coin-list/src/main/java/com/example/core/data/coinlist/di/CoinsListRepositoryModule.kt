package com.example.core.data.coinlist.di

import com.example.core.data.coinlist.repositoryimpl.CoinsListRepositoryImpl
import com.example.core.domain.domaincoinslist.repository.CoinsListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface CoinsListRepositoryModule {
    @Binds
    fun bindCoinsRepository(impl: CoinsListRepositoryImpl): CoinsListRepository
}
