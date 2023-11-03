package com.example.domain.domainsettings.entityContract

interface SettingsStateTransmitter {
    val capacity: Number
    val tagsHolder: List<String>
    val ticker: String
    val sortBy: String
    val isAsc: Boolean
}
