package com.example.domain.domainsettings.repository

import com.example.domain.domainsettings.entityContract.SettingsStateTransmitter

interface SettingsStateApplier {

    fun setSortListBy(transmitter: SettingsStateTransmitter)
    fun setCapacity(transmitter: SettingsStateTransmitter)
    fun setTicker(transmitter: SettingsStateTransmitter)
    fun setTags(transmitter: SettingsStateTransmitter)
}
