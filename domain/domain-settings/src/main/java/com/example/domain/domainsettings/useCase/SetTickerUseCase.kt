package com.example.domain.domainsettings.useCase

import com.example.domain.domainsettings.contract.TickerSetter
import com.example.domain.domainsettings.repository.SettingsStateApplier
import javax.inject.Inject

internal class SetTickerUseCase @Inject constructor(private val applier: SettingsStateApplier) :
    TickerSetter {
    override fun invoke(ticker: String) {
        applier.setTicker(StateTransmitter(ticker = ticker))
    }
}
