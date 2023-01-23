package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.data.CoinsRepositoryImpl
import com.example.thindie.wantmoex.domain.EntityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindEntityRepo(coinsRepositoryImpl: CoinsRepositoryImpl): EntityRepository
}
