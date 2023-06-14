package com.example.domain.domainsettings.contract

interface SettingsProvider {
    fun getCapacity(): Int
    fun getSort(): String
    fun getStraight(): Boolean
}
