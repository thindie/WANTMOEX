package com.example.core.database.di

import android.app.Application
import androidx.room.Room
import com.example.core.database.instance.CoinsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
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
