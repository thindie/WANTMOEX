package com.example.domain.domainsettings.useCase

import com.example.domain.domainsettings.contract.CoinsCapacitySetter
import com.example.domain.domainsettings.repository.SettingsStateApplier
import javax.inject.Inject

internal class SetLimitUseCase @Inject constructor(private val applier: SettingsStateApplier) :
    CoinsCapacitySetter {
    override fun basicCapacity() {
        applier.setCapacity(StateTransmitter(capacity = BASIC_CAPACITY))
    }

    override fun advancedCapacity() {
        applier.setCapacity(StateTransmitter(capacity = ADVANCED_CAPACITY))
    }

    override fun expertCapacity() {
        applier.setCapacity(StateTransmitter(capacity = EXPERT_CAPACITY))
    }
}
