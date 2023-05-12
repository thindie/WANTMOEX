package com.example.thindie.wantmoex.data.storage

import com.example.thindie.wantmoex.data.mappers.map
import com.example.thindie.wantmoex.data.storage.allCoins.CoinDBModel
import com.example.thindie.wantmoex.data.storage.allCoins.CoinDao
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.encapsulateResult
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalCoinRepositoryImpl @Inject constructor(
    private val local: CoinDao,
) : LocalCoinRepository {

    override fun observeAllCoins(): Flow<Results<List<CoinDBModel>>> {
        return flow {
            local.observeAllCoins().collect {
                emit(it.encapsulateResult())
            }
        }
    }

    override fun observeAllCoins(limit: Int): Flow<Results<List<CoinDBModel>>> {
        return observeAllCoins()
    }

    override fun observeCoin(fromSymbol: String): Flow<Results<CoinDBModel>> {
        return flow {
            local.observeCoin(fromSymbol).collect {
                emit(it.encapsulateResult())
            }
        }
    }

    override suspend fun addCoins(list: List<Coin>) {
        val dbList = list.map {
            it.map()
        }
        local.insertPriceList(dbList)
    }

    override suspend fun getCoin(fromSymbol: String): Results<CoinDBModel> {
        return local.getCoin(fromSymbol).encapsulateResult()
    }

    override suspend fun getAllCoins(): Results<List<CoinDBModel>> {
        return local.getAllCoins().encapsulateResult()
    }

    override suspend fun getAllCoins(limit: Int): Results<List<CoinDBModel>> {
        return getAllCoins()
    }

}