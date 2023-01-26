package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.data.CryptoCoinsRepositoryImpl
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindEntityRepo(cryptoCoinsRepositoryImpl: CryptoCoinsRepositoryImpl): CryptoCoinRepository
}
