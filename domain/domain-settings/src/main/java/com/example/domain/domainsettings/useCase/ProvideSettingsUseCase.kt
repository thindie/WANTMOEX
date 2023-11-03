package com.example.domain.domainsettings.useCase

import com.example.domain.domainsettings.contract.SettingsProvider
import com.example.domain.domainsettings.repository.SettingsStateProvider
import javax.inject.Inject

class ProvideSettingsUseCase @Inject constructor(private val settingsStateProvider: SettingsStateProvider) :
    SettingsProvider {
    override fun getCapacity(): Int {
        return settingsStateProvider.getListStateSettings().capacity.toInt()
    }

    override fun getSort(): String {
        return settingsStateProvider.getListStateSettings().sortBy
    }

    override fun getStraight(): Boolean {
        return settingsStateProvider.getListStateSettings().isAsc
    }
}
