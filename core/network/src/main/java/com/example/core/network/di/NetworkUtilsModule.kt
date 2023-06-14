package com.example.core.network.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 *API HERE:
 * https://min-api.cryptocompare.com/documentation
 *
 **/

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkUtilsModule {

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain
                .proceed(chain.request())
            Log.d("SERVICE_TAG_INTERCEPTOR", ("Outgoing request to ${response.request().url()}"))
            if (!response.isSuccessful) {
                Log.d("SERVICE_TAG_UNSUCCESSFUL_NETWORK", "${response.code()} - response code")
            }
            response
        }
    }

    @Provides
    fun provideOkhttp(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
}
