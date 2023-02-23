package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.map
import com.example.thindie.wantmoex.data.network.RemoteCoinRepository
import com.example.thindie.wantmoex.data.storage.LocalCoinRepository
import com.example.thindie.wantmoex.domain.*
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCoinsRepositoryImpl @Inject constructor(
    private val remoteCoinRepository: RemoteCoinRepository,
    private val localCoinRepository: LocalCoinRepository,
) : CryptoCoinRepository {


    override fun observeAllCoins(limit: Int): Flow<Results<List<Coin>>> {
        return flow {
            remoteCoinRepository.observeAllCoins(limit).collect {
                val results = it.reDirectSource({ getAllCoins(limit) }) {
                    it.map { it.map().map() }
                }
                emit(results)
            }
        }.handleErrors()
    }

    override fun observeCoin(fromSymbol: String): Flow<Results<Coin>> {
        return flow {
            remoteCoinRepository.observeCoin(fromSymbol).collect {
                val result = it.reDirectSource({ getCoin(fromSymbol) }) {
                    it.map().map()
                }
                emit(result)
            }
        }.handleErrors()

    }

    override suspend fun getCoin(fromSymbol: String): Results<Coin> {

        remoteCoinRepository.forkDataSources(localCoinRepository)?.getCoin(fromSymbol)
            ?: return remoteCoinRepository.getCoin(fromSymbol).result { it.map().map() }

        return localCoinRepository.getCoin(fromSymbol).result {
            it.map()
        }
    }


    override suspend fun getAllCoins(limit: Int): Results<List<Coin>> {
        remoteCoinRepository.forkDataSources(localCoinRepository)?.getAllCoins(limit)
            ?: remoteCoinRepository.getAllCoins(limit).unpackResult { it.map { it.map().map() } }
                .apply {
                    localCoinRepository.addCoins(this ?: emptyList())
                    return this!!.encapsulateResult()
                }

        return localCoinRepository.getAllCoins(limit).result { it.map { it.map() } }

    }

}

private suspend fun RemoteCoinRepository.forkDataSources(localCoinRepository: LocalCoinRepository): LocalCoinRepository? {
    return if (this.getAllCoins().result { it } is Results.Error) localCoinRepository else null
}




