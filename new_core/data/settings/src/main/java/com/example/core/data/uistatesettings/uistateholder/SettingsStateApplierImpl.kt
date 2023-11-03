package com.example.core.data.uistatesettings.uistateholder

import com.example.core.data.uistatesettings.uistateholder.SettingsStateApplierImpl.SettingsStateReceiver
import com.example.domain.domainsettings.entityContract.SettingsStateTransmitter
import com.example.domain.domainsettings.repository.SettingsStateApplier
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This code defines an implementation of the [SettingsStateApplier] interface that applies changes
 * to the settings state based on the user's actions, such as changing the capacity, ticker or sorting order.
 * The [ChainStateLink] interface is implemented to provide a way for other components to
 * access the current state of the settings. The [TickerTransmitter] and [TagsTransmitter]
 * interfaces provide access to the current ticker and list of tags, respectively.
 * The state changes are triggered through the  [setSortListBy], [setCapacity],
 * [setTicker], and setTags functions, which take a [SettingsStateTransmitter] object as an argument.
 * The settingsState variable is updated based on the new state received through the transmitter.
 * The [SettingsStateReceiver] data class is used to store the new state received from the transmitter,
 * and it implements the [SettingsStateTransmitter] interface
 * to standardize the representation of the new state across the app.
 * */
@Singleton
internal class SettingsStateApplierImpl @Inject constructor(transmitter: SettingsStateTransmitter) :
    SettingsStateApplier, ChainStateLink, TickerTransmitter, TagsTransmitter {

    /**
     * How this can be better?
     * */

    private val settingsState: MutableList<SettingsStateTransmitter> =
        mutableListOf(transmitter)


    override fun setSortListBy(transmitter: SettingsStateTransmitter) {
        settingsState += onPrepare().copy(sortBy = transmitter.sortBy, isAsc = transmitter.isAsc)
    }

    override fun setCapacity(transmitter: SettingsStateTransmitter) {
        settingsState += onPrepare().copy(capacity = transmitter.capacity)
    }

    override fun setTicker(transmitter: SettingsStateTransmitter) {
        settingsState += onPrepare().copy(ticker = transmitter.ticker)
    }

    override fun setTags(transmitter: SettingsStateTransmitter) {
        settingsState += onPrepare().copy(tagsHolder = transmitter.tagsHolder)
    }

    private fun onPrepare(): SettingsStateReceiver {
        val getCurrent = settingsState.last()
        settingsState.clear()
        return getCurrent.landToReceiver()
    }

    override fun provideSettings(): SettingsStateTransmitter {
        return settingsState.last()
    }

    override fun transmitTicker(): String {
        return settingsState.last().ticker
    }

    override fun transmitTags(): List<String> {
        return settingsState.last().tagsHolder
    }

    private fun SettingsStateTransmitter.landToReceiver(): SettingsStateReceiver {
        return SettingsStateReceiver(
            this.capacity,
            this.tagsHolder,
            this.ticker,
            this.sortBy,
            this.isAsc
        )
    }

    internal data class SettingsStateReceiver(
        override val capacity: Number,
        override val tagsHolder: List<String>,
        override val ticker: String,
        override val sortBy: String,
        override val isAsc: Boolean,
    ) : SettingsStateTransmitter
}

interface ChainStateLink {
    fun provideSettings(): SettingsStateTransmitter
}

internal interface TickerTransmitter {
    fun transmitTicker(): String
}

internal interface TagsTransmitter {
    fun transmitTags(): List<String>
}
