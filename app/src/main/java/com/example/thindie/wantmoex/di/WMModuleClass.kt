package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.data.network.RetrofitFactory
import com.example.thindie.wantmoex.data.network.StockApiService
import dagger.Module
import dagger.Provides

@Module
class WMModuleClass {
    @Provides
    fun provideApi(): StockApiService {
        return RetrofitFactory.apiService
    }

}