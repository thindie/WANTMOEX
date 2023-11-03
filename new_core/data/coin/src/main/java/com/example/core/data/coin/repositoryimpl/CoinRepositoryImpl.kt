package com.example.core.data.coin.repositoryimpl

import com.example.core.data.coin.mapper.coinDbModelToCoin
import com.example.core.database.instance.CoinDao
import com.example.core.domain.domaincoin.entity.Coin
import com.example.core.domain.domaincoin.repository.CoinRepository
import com.example.domain.domainsettings.repository.SettingsStateProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * * depends on [CoinDao] Room db and [SettingsStateProvider] which is collect and hold params
 *  kinda settings from UI
 * */
@Singleton
internal class CoinRepositoryImpl @Inject constructor(
    private val dao: CoinDao,
    private val observer: SettingsStateProvider,
) : CoinRepository {

    private val confirmedTicker = observer::getAppliedTicker
    override suspend fun getCoin() = get(confirmedTicker())

    /**
     * Cannot throw any exceptions, [Coin] legit is stable with DAO as sst
     */
    private suspend fun get(id: String): Coin {
        return dao.getCoin(id).coinDbModelToCoin()
    }
}
