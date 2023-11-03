package com.example.core.database.di

import com.example.core.database.instance.CoinDao
import com.example.core.database.instance.CoinsDataBase
import dagger.Module
import dagger.Provides

@Module
internal class CoinDaoModule {

    @Provides
    fun provideCoinDao(appDataBase: CoinsDataBase): CoinDao {
        return appDataBase.getInstance()
    }
}