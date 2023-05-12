package com.example.domain.domainsettings.repository

import com.example.domain.domainsettings.entityContract.SettingsStateTransmitter

interface SettingsStateProvider {
    fun getListStateSettings(): SettingsStateTransmitter
    fun getAppliedTags(): List<String>
    fun getAppliedTicker(): String // interface segregation ?! //todo(
}
