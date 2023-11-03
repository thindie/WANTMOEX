package com.example.domain.domainsettings.contract

interface SortListTrigger {
    fun byPrice()
    fun byPriceDesc()
    fun byTicker()
    fun byTickerDesc()
    fun byLastUpdate()
    fun byLastUpdateDesc()
    fun byPercentageDelta()
    fun byPercentageDeltaDesc()
}
