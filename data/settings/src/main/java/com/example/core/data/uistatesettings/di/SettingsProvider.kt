package com.example.core.data.uistatesettings.di

import com.example.thindie.domain.Repository
import com.example.thindie.domain.options.Option

interface SettingsProvider {
    fun provideSettingsRepository(): Repository<Option>
}
