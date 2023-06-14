package com.example.core.domain.domainnewsfeed.di

import com.example.core.domain.domainnewsfeed.contract.NewsFetcher
import com.example.core.domain.domainnewsfeed.usecase.GetNewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NewsFetcherModule {
    @Binds
    fun bindNewsFetcher(useCase: GetNewsUseCase): NewsFetcher
}
