package com.example.core.network.di

import com.example.core.network.retrofit.NetworkService
import com.example.core.network.retrofit.RetrofitNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkServiceModule {
    @Binds
    fun bindNetworkService(impl: RetrofitNetwork): NetworkService
}
