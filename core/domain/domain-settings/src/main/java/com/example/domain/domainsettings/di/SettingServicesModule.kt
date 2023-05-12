package com.example.domain.domainsettings.di

import com.example.domain.domainsettings.contract.CoinsCapacitySetter
import com.example.domain.domainsettings.contract.NewsTagsSetter
import com.example.domain.domainsettings.contract.SettingsProvider
import com.example.domain.domainsettings.contract.SortListTrigger
import com.example.domain.domainsettings.contract.TickerSetter
import com.example.domain.domainsettings.useCase.ProvideSettingsUseCase
import com.example.domain.domainsettings.useCase.SetLimitUseCase
import com.example.domain.domainsettings.useCase.SetListSortUseCse
import com.example.domain.domainsettings.useCase.SetNewsTagsUseCase
import com.example.domain.domainsettings.useCase.SetTickerUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TagsSetterModule {
    @Binds
    fun bindTagsSetter(tagsUseCase: SetNewsTagsUseCase): NewsTagsSetter
}

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinsParamsSetterModule {
    @Binds
    fun bindCoinsParamsSetter(setLimitUseCase: SetLimitUseCase): CoinsCapacitySetter
}

@Module
@InstallIn(SingletonComponent::class)
internal interface CoinTickerSetterModule {
    @Binds
    fun bindTickerSetter(useCase: SetTickerUseCase): TickerSetter
}

@Module
@InstallIn(SingletonComponent::class)
internal interface ListSorterModule {
    @Binds
    fun bindsListSorterModule(useCase: SetListSortUseCse): SortListTrigger
}

@Module
@InstallIn(SingletonComponent::class)
internal interface SettingsProviderModule {
    @Binds
    fun bindSettingsProvider(useCase: ProvideSettingsUseCase): SettingsProvider
}