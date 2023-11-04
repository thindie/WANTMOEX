package com.example.core.network.di

import com.example.core.network.apiservice.ApiService

interface NetworkProvider {
    fun provideRetrofit(): ApiService
}
