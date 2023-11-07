package com.example.core.data.coinlist.repositoryimpl


import com.example.core.data.coinlist.mapper.coinDtoToCoin
import com.example.core.network.apiservice.ApiService
import com.example.thindie.domain.Event
import com.example.thindie.domain.Repository
import com.example.thindie.domain.SimpleRequest
import com.example.thindie.domain.entities.Coin
import com.example.thindie.domain.options.Currency
import com.example.thindie.domain.options.Filter
import com.example.thindie.domain.options.Limit
import com.example.thindie.domain.options.Option
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update

internal class CoinsRepositoryImpl(private val service: ApiService) : Repository<List<Coin>> {

    private val eventStateFlow = MutableStateFlow<SimpleEvent?>(null)
    override suspend fun request(request: SimpleRequest) {
        when (request) {
            SimpleRequest.Refresh -> {
                val coins = service.getTopCoinsEURO(limit = 10).getPopulated()
                eventStateFlow.update { event ->
                    event?.copy(list = coins.map { coinDto -> coinDto.coinDtoToCoin() })
                        ?: SimpleEvent(coins.map { it.coinDtoToCoin() })
                }
            }

            is SimpleRequest.Set<*> -> {
                val order = request.t
                when (order::class) {
                    Option::class -> {
                        order as Option
                        when (order) {
                            Currency.JPY -> TODO()
                            Currency.RUB -> TODO()
                            Currency.EUR -> TODO()
                            Currency.USD -> TODO()
                            Filter.ALPHABET -> TODO()
                            Filter.PRICE -> TODO()
                            Filter.GROW -> TODO()
                            Limit.TEN -> TODO()
                            Limit.THIRTY -> TODO()
                            Limit.FIFTY -> TODO()
                        }
                    }
                }
            }
        }
    }

    override fun observeEvent(): Flow<Event<List<Coin>>> {
        return eventStateFlow
            .filterNotNull()
    }

    data class SimpleEvent(val list: List<Coin>) : Event<List<Coin>> {
        override fun onEvent() = list
    }
}
