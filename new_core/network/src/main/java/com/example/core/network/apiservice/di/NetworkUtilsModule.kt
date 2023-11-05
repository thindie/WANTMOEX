package com.example.core.network.apiservice.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 *API HERE:
 * https://min-api.cryptocompare.com/documentation
 *
 **/

@Module
internal class NetworkUtilsModule {

    @Provides
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .build()
    }
}
