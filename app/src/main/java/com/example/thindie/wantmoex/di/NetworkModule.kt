package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.data.network.retrofit.RetrofitFactory
import com.example.thindie.wantmoex.data.network.retrofit.StockApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
    @InstallIn(SingletonComponent::class)
    class NetworkModule {
        @Provides
        fun provideStockApiService(): StockApiService {
            return RetrofitFactory.apiService
        }
    }