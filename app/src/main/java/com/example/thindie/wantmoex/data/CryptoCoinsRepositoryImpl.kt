package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.entityMap
import com.example.thindie.wantmoex.data.mappers.map
import com.example.thindie.wantmoex.data.network.RemoteCoinRepository
import com.example.thindie.wantmoex.data.network.dto.CoinDTO
import com.example.thindie.wantmoex.data.storage.LocalCoinRepository
import com.example.thindie.wantmoex.data.storage.allCoins.CoinDBModel
import com.example.thindie.wantmoex.domain.*
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCoinsRepositoryImpl @Inject constructor(
    private val remoteCoinRepository: RemoteCoinRepository,
    private val localCoinRepository: LocalCoinRepository,
) : CryptoCoinRepository {


    override fun observeAllCoins(limit: Int): Flow<Results<List<Coin>>> {
        return remoteCoinRepository.observeAllCoins().mutateFlow {
            it.map { it.map().map() }
        }.handleErrors()
    }

    override fun observeCoin(fromSymbol: String): Flow<Results<Coin>> {
        return remoteCoinRepository.observeCoin(fromSymbol).mutateFlow {
            it.map().map()
        }.handleErrors()
    }

    override suspend fun getCoin(fromSymbol: String): Results<Coin> {
        return remoteCoinRepository.getCoin(fromSymbol).result {
            it.map().map()
        }
    }

    override suspend fun getAllCoins(limit: Int): Results<List<Coin>> {
        return remoteCoinRepository.getAllCoins(limit).result {
            it.map {
                it.map().map()
            }
        }
    }

}

