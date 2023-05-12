package com.example.core.data.uistatesettings.di

import com.example.core.data.uistatesettings.uistateholder.ChainStateLink
import com.example.core.data.uistatesettings.uistateholder.SettingsStateApplierImpl
import com.example.core.data.uistatesettings.uistateholder.SettingsStateProviderImpl
import com.example.core.data.uistatesettings.uistateholder.TagsTransmitter
import com.example.core.data.uistatesettings.uistateholder.TickerTransmitter
import com.example.domain.domainsettings.repository.SettingsStateApplier
import com.example.domain.domainsettings.repository.SettingsStateProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SettingsApplierModule {
    @Binds
    fun bindSettingsApplier(impl: SettingsStateApplierImpl): SettingsStateApplier
}

@Module
@InstallIn(SingletonComponent::class)
internal interface UiStateSettingsObserverModule {
    @Binds
    fun bindUiStateSettingsObserver(impl: SettingsStateProviderImpl): SettingsStateProvider
}

@Module
@InstallIn(SingletonComponent::class)
internal interface ChainLinkModule {
    @Binds
    fun bindChainLink(impl: SettingsStateApplierImpl): ChainStateLink
}

@Module
@InstallIn(SingletonComponent::class)
internal interface TickerTransmitterModule {
    @Binds
    fun bindTickerTransmitter(impl: SettingsStateApplierImpl): TickerTransmitter
}

@Module
@InstallIn(SingletonComponent::class)
internal interface TagsTransmitterModule {
    @Binds
    fun bindTagsTransmitter(impl: SettingsStateApplierImpl): TagsTransmitter
}
