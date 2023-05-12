package com.example.domain.domainsettings.di

import com.example.domain.domainsettings.entityContract.SettingsStateTransmitter
import com.example.domain.domainsettings.useCase.StateTransmitter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class StateCourierModule {
    @Provides
    fun provideStateCourier(): SettingsStateTransmitter {
        return StateTransmitter()
    }
}
