package com.example.core.data.uistatesettings.di

import com.example.core.data.uistatesettings.repository.SettingsRepositoryImpl
import com.example.thindie.domain.Repository
import com.example.thindie.domain.options.Option
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class SettingsRepositoryModule {
    @Provides
    @Singleton
    fun provideSettingsRepository(): Repository<Option> {
        return SettingsRepositoryImpl()
    }
}
