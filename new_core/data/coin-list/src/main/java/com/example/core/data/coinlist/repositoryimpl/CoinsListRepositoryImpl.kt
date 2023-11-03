package com.example.core.data.coinlist.repositoryimpl

import com.example.core.common.commarequestbuilder.commaQueryEncodedBuilder
import com.example.core.common.consts.REFRESHING_RATE
import com.example.core.common.dispatcherscoroutine.DispatchersModule
import com.example.core.data.coinlist.mapper.coinDbModelToCoin
import com.example.core.data.coinlist.mapper.toCoinDbModelWithCheckedFavoriteStatus
import com.example.core.database.instance.CoinDao
import com.example.core.database.instance.CoinDbModel
import com.example.core.domain.domaincoinslist.entity.Coin
import com.example.core.domain.domaincoinslist.entity.CoinsListSettings
import com.example.core.domain.domaincoinslist.repository.CoinsListRepository
import com.example.core.network.dto.CoinDto
import com.example.core.network.retrofit.NetworkService
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Suppress("ConstructorParameterNaming")
@Singleton
internal class CoinsListRepositoryImpl @Inject constructor(
    private val service: NetworkService,
    private val dao: CoinDao,
    @DispatchersModule.IODispatcher private val IOdispatcher: CoroutineDispatcher,
) : CoinsListRepository {

    /**
     * Observe the list of coins based on the given [settings].
     * settings the settings for observing the coins list, such as the capacity of the list
     * a [Flow] emitting a new list of [Coin] objects whenever there is a change in the database.
     * */

    override fun observeCoins(settings: CoinsListSettings): Flow<List<Coin>> {
        return dao.observeAllCoins(capacity = settings.capacity)
            .map { daoCoins ->
                daoCoins
                    .filter { dbModel -> if (settings.isFavorites) dbModel.isFavorite else true }
                    .map { dbModel -> dbModel.coinDbModelToCoin() }
            }
            .flowOn(IOdispatcher)
    }

    /**
     *  [actualizeObservingSource]
     *  Updates the observing source by fetching the latest data from the API
     *  and storing it in the local database.
     *  The method runs in an infinite loop and checks for updates at specified [REFRESHING_RATE].
     *  If the database already contains saved entities,
     *  it only updates those. Otherwise, it fetches the top coins
     *  as per the provided capacity [COINS_CAPACITY].
     *  The favorite status for each coin is determined based on the data stored
     *  in the database.
     *  @see toCoinDbModelWithCheckedFavoriteStatus
     *  @see commaQueryEncodedBuilder
     *  @return Returns false if an error occurs during the
     *  execution of the method, such as an unknown host exception, todo().
     */

    override suspend fun actualizeObservingSource(): Boolean {
        try {
            while (true) {
                val savedCoinsTickers = dao.getTickers()
                if (savedCoinsTickers.isEmpty()) {
                    requestServiceFillDao()
                } else {
                    delay(REFRESHING_RATE)
                    val buildUpsertList =
                        savedCoinsTickers
                            .fetchFromService()
                            .map { fetchedDto ->
                                mapDto(fetchedDto)
                            }
                    require(buildUpsertList.isNotEmpty()) {
                        " where:'actualizeObservingSource' in " +
                                " data module CoinListRepositoryImpl " +
                                " why: repo has zero data from dao and service " +
                                " result: returned flag 'false' to repeat try "
                    }
                    dao.upsertList(buildUpsertList)
                }
            }
        } catch (e: IllegalArgumentException) {
            return false
        }
    }

    private suspend fun requestServiceFillDao() {
        val firstLaunchDtoList = service
            .getCoins(COINS_CAPACITY)
        val dbModelList = firstLaunchDtoList.map { dto ->
            dto.toCoinDbModelWithCheckedFavoriteStatus(false)
        }
        dao.upsertList(dbModelList)
    }

    private suspend fun List<String>.fetchFromService(): List<CoinDto> {
        return withContext(IOdispatcher) {
            service
                .getCoin(commaQueryEncodedBuilder(this@fetchFromService))
        }
    }

    private suspend fun mapDto(dto: CoinDto): CoinDbModel {
        val idToUpsert = dto.fromSymbol
        val dbModelByFreshFetchedID = withContext(IOdispatcher) {
            dao.getCoin(idToUpsert)
        }
        return dto
            .toCoinDbModelWithCheckedFavoriteStatus(dbModelByFreshFetchedID.isFavorite)
    }

    companion object {
        private const val COINS_CAPACITY = 100
    }
}
