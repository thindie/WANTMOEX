package com.example.domain.domainsettings.useCase

import com.example.domain.domainsettings.contract.SortListTrigger
import com.example.domain.domainsettings.repository.SettingsStateApplier
import javax.inject.Inject

internal class SetListSortUseCse @Inject constructor(private val applier: SettingsStateApplier) :
    SortListTrigger {
    override fun byPrice() {
        applier.setSortListBy(StateTransmitter(sortBy = SORT_PRICE, isAsc = true))
    }

    override fun byPriceDesc() {
        applier.setSortListBy(StateTransmitter(sortBy = SORT_PRICE))
    }

    override fun byTicker() {
        applier.setSortListBy(StateTransmitter(sortBy = SORT_TICKER, isAsc = true))
    }

    override fun byTickerDesc() {
        applier.setSortListBy(StateTransmitter(sortBy = SORT_TICKER))
    }

    override fun byLastUpdate() {
        applier.setSortListBy(StateTransmitter(sortBy = SORT_UPDATED, isAsc = true))
    }

    override fun byLastUpdateDesc() {
        applier.setSortListBy(StateTransmitter(sortBy = SORT_UPDATED))
    }

    override fun byPercentageDelta() {
        applier.setSortListBy(StateTransmitter(sortBy = SORT_PERCENT_DELTA, isAsc = true))
    }

    override fun byPercentageDeltaDesc() {
        applier.setSortListBy(StateTransmitter(sortBy = SORT_PERCENT_DELTA))
    }
}
