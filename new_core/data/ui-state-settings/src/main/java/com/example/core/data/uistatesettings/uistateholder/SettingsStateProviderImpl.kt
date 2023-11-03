package com.example.core.data.uistatesettings.uistateholder

import com.example.domain.domainsettings.entityContract.SettingsStateTransmitter
import com.example.domain.domainsettings.repository.SettingsStateProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SettingsStateProviderImpl @Inject constructor(
    private val link: ChainStateLink,
    private val tickerTransmitter: TickerTransmitter,
    private val tagsTransmitter: TagsTransmitter,
) :
    SettingsStateProvider {
    override fun getListStateSettings(): SettingsStateTransmitter {
        return link.provideSettings()
    }

    override fun getAppliedTags(): List<String> {
        return tagsTransmitter.transmitTags()
    }

    override fun getAppliedTicker(): String {
        return tickerTransmitter.transmitTicker()
    }
}
