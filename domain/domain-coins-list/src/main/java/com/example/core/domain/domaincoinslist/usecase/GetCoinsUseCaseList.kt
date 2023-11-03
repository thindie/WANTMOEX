package com.example.core.domain.domaincoinslist.usecase

import com.example.core.common.dispatcherscoroutine.DispatchersModule
import com.example.core.domain.domaincoinslist.contract.CoinsListFetcher
import com.example.core.domain.domaincoinslist.entity.Coin
import com.example.core.domain.domaincoinslist.entity.CoinsListSettings
import com.example.core.domain.domaincoinslist.repository.CoinsListRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class GetCoinsUseCaseList @Inject constructor(
    private val repository: CoinsListRepository,
    @DispatchersModule.MainDispatcher private val dispatcherMain: CoroutineDispatcher,
) : CoinsListFetcher {

    private var isAlreadyFetching = false

    override suspend fun fetchCoinsListDependOn(settings: CoinsListSettings): Flow<List<Coin>> {
        if (!isAlreadyFetching) {
            isAlreadyFetching = true
            isAlreadyFetching = repository.actualizeObservingSource()
        }
        return repository.observeCoins(settings)
            .map { list -> list.sortBy(settings.sortedBy, settings.isAsc) }
            .flowOn(dispatcherMain)
    }

    /**
     * Sorts a given list of coins based on the provided sorting method and order.
     * [sorterTag] String representing the sorting method
     * to be used (e.g. [SORT_TICKER], [SORT_PRICE], [SORT_UPDATED], [SORT_PERCENT_DELTA])
     * [isAsc] Boolean representing the sort order,
     * where true indicates ascending order and false indicates descending order.
     * @return A new sorted list of coins based on the sorting method and order provided.
     *  p.s Yes I know that Dao can sort cases
     */
    private fun List<Coin>.sortBy(sorterTag: String, isAsc: Boolean): List<Coin> {
        val toReturn = this.toMutableList()
        return when (sorterTag) {
            SORT_TICKER -> {
                toReturn.sortWith { c0, c1 ->
                    if (isAsc) {
                        c0.compareTo(c1)
                    } else {
                        c1.compareTo(c0)
                    }
                }; toReturn.toList()
            }
            SORT_PRICE -> {
                toReturn.sortWith { c0, c1 ->
                    if (isAsc) {
                        /**
                         * [P_P_H] aka Parse probability helper. Make toInt() rounded problems solved
                         */
                        ((c0.price.toFloat() * P_P_H).toInt() - (c1.price.toFloat() * P_P_H).toInt())
                    } else {
                        ((c1.price.toFloat() * P_P_H).toInt() - (c0.price.toFloat() * P_P_H).toInt())
                    }
                }; toReturn.toList()
            }
            SORT_UPDATED -> {
                toReturn.sortWith { c0, c1 ->
                    if (isAsc) {
                        c0.lastUpdate.toInt() - c1.lastUpdate.toInt()
                    } else {
                        c1.lastUpdate.toInt() - c0.lastUpdate.toInt()
                    }
                }
                toReturn.toList()
            }
            SORT_PERCENT_DELTA -> {
                toReturn.sortWith { c0, c1 ->
                    if (isAsc) {
                        (c0.percentDelta.parseStringPercent() - c1.percentDelta.parseStringPercent()).toInt()
                    } else {
                        (c1.percentDelta.parseStringPercent() - c0.percentDelta.parseStringPercent()).toInt()
                    }
                }; toReturn.toList()
            }
            else -> toReturn.toList()
        }
    }

    /**
     *  service fun to [sortBy]
     *  gets string able to parse from [Coin] 'percentDelta'
     *  by reduce '%' and '+ / -' symbols
     *
     * */

    private fun String.parseStringPercent(): Double {
        return if (this.first().toString() == "+") {
            val prepareToParse = this.replaceFirst("+", "").replace("%", "")
            prepareToParse.toDouble() * PARSE_PROBABILITY_MAGIC_NUMBER
        } else {
            val prepareToParse = this.replaceFirst("-", "").replace("%", "")
            1.00 - prepareToParse.toDouble() * PARSE_PROBABILITY_MAGIC_NUMBER
        }
    }
}

private const val PARSE_PROBABILITY_MAGIC_NUMBER = 301
private const val P_P_H = 25
