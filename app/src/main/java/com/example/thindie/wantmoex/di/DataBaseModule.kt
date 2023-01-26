package com.example.thindie.wantmoex.di

import android.app.Application
import com.example.thindie.wantmoex.data.storage.AppDataBase
import com.example.thindie.wantmoex.data.storage.favourites.FavouriteCoinsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideDataBase(application: Application): AppDataBase {
        return AppDataBase.getInstance(application)
    }

    @Provides
    fun provideFavouriteCoinsDataBase(application: Application): FavouriteCoinsDataBase{
        return FavouriteCoinsDataBase.getInstance(application)
    }
}