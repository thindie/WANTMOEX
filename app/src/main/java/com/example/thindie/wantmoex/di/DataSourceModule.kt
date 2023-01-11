package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.data.network.RemoteDataSource
import com.example.thindie.wantmoex.data.network.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {


    @Binds
    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
}
