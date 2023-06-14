package com.example.core.database.di

import android.app.Application
import androidx.room.Room
import com.example.core.database.instance.CoinDao
import com.example.core.database.instance.CoinsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun provideCoinDao(appDataBase: CoinsDataBase): CoinDao {
        return appDataBase.getInstance()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(application: Application): CoinsDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = CoinsDataBase::class.java,
            name = DB_NAME
        ).build()
    }
}

private const val DB_NAME = "coins.db"
