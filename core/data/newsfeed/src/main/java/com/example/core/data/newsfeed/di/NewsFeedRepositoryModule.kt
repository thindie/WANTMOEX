package com.example.core.data.newsfeed.di

import com.example.core.data.newsfeed.NewsFeedRepositoryImpl
import com.example.core.domain.domainnewsfeed.repository.NewsFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NewsFeedRepositoryModule {
    @Binds
    fun bindNewsRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository
}
