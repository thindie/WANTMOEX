package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.data.StockDataRepositoryImpl
import com.example.thindie.wantmoex.domain.StockRepository
import dagger.Binds
import dagger.Module

@Module
interface WMModule {


    @Binds
    fun bindStockDataRep(impl: StockDataRepositoryImpl): StockRepository

}