package com.example.core.data.uistatesettings.repository

import com.example.thindie.domain.Event
import com.example.thindie.domain.Repository
import com.example.thindie.domain.SimpleRequest
import com.example.thindie.domain.options.Option
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

@Singleton
internal class SettingsRepositoryImpl : Repository<Option> {

    private val settingsState = MutableStateFlow<SimpleEvent?>(null)
    override suspend fun request(request: SimpleRequest) {
        when (request) {
            SimpleRequest.Refresh -> {}
            is SimpleRequest.Set<*> -> {
                when (request.t::class) {
                    Option::class -> {
                        settingsState.value = SimpleEvent(request.t as Option)
                    }

                    else -> {}
                }
            }
        }
    }

    override fun observeEvent(): Flow<Event<Option>> {
        return settingsState
            .filterNotNull()
    }

    data class SimpleEvent(val option: Option) : Event<Option> {
        override fun onEvent() = option
    }
}
