package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.map
import com.example.thindie.wantmoex.data.network.RemoteCoinRepository
import com.example.thindie.wantmoex.data.storage.LocalCoinRepository
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.Results.Success
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCoinsRepositoryImpl @Inject constructor(
    private val remoteCoinRepository: RemoteCoinRepository,
    private val localCoinRepository: LocalCoinRepository,
) : CryptoCoinRepository {


    override fun observeAllCoins(): Flow<Results<List<Coin>>> {
        return observeAllCoins(UNSETTED_LIMIT)
    }

    override fun observeAllCoins(limit: Int): Flow<Results<List<Coin>>> {
        val flowState: MutableStateFlow<Results<List<Coin>>> =
            MutableStateFlow(Success(emptyList()))
        remoteCoinRepository.observeAllCoins().map { remoteResult ->

            when (remoteResult) {
                is Success -> {
                    val webResult = remoteResult.transform {
                        it.map {
                            it.map().map()
                        }
                    }
                    flowState.value = webResult
                }
                is Results.Error -> {
                    localCoinRepository.observeAllCoins().map { localResult ->
                        when (localResult) {
                            is Success -> {
                                val dBResult = localResult.transform {
                                    it.map {
                                        it.map()
                                    }
                                }
                                flowState.value = dBResult
                            }
                            is Results.Error -> {
                                flowState.value = Results.Error(Exception("no observable data"))
                            }
                        }

                    }
                }
            }
        }
        return flow { emit(flowState.value) }
    }

    override fun observeCoin(fromSymbol: String): Flow<Results<Coin>> {
        val flowState: MutableStateFlow<Results<Coin>> =
            MutableStateFlow(Results.Error(Exception("empty data")))

        remoteCoinRepository.observeCoin(fromSymbol).map { remoteCoinObserve ->
            when (remoteCoinObserve) {
                is Success -> {
                    val remoteCoin = remoteCoinObserve.transform {
                        it.map().map()
                    }
                    flowState.value = remoteCoin
                }
                is Results.Error -> {
                    localCoinRepository.observeCoin(fromSymbol).map { localCoinObserve ->
                        when (localCoinObserve) {
                            is Success -> {
                                val localCoin = localCoinObserve.transform {
                                    it.map()
                                }
                                flowState.value = localCoin
                            }
                            is Results.Error -> {}
                        }
                    }
                }
            }
        }
        return flow { emit(flowState.value) }
    }

    override suspend fun getCoin(fromSymbol: String): Results<Coin> {

        when (val remoteCoin = remoteCoinRepository.getCoin(fromSymbol)) {
            is Success -> {
                return remoteCoin.transform {
                    it.map().map()
                }
            }

            is Results.Error -> {
                when (val localCoin = localCoinRepository.getCoin(fromSymbol)) {
                    is Success -> {
                        return localCoin.transform {
                            it.map()
                        }
                    }

                    is Results.Error ->
                        return Results.Error(Exception("bad data in local and remote sources"))

                }
            }
            else -> {
                return Results.Error(Exception("bad data in local and remote sources"))
            }
        }
    }

    override suspend fun getAllCoins(): Results<List<Coin>> {
        return getAllCoins(UNSETTED_LIMIT)
    }

    override suspend fun getAllCoins(limit: Int): Results<List<Coin>> {
        when (val remoteCoins = remoteCoinRepository.getAllCoins(limit = limit)) {
            is Success -> {
                return remoteCoins.transform {
                    it.map {
                        it.map().map()
                    }
                }
            }
            is Results.Error -> {
                when (val localCoins = localCoinRepository.getAllCoins(limit)) {
                    is Success -> {
                        return localCoins.transform {
                            it.map {
                                it.map()
                            }
                        }
                    }
                    is Results.Error -> {
                        return Results.Error(Exception("bad data in local and remote sources"))
                    }
                }
            }
        }
    }

    companion object {
        private const val UNSETTED_LIMIT = 10
    }
}

fun <T, R> Success<T>.transform(mapper: (T) -> R): Success<R> {
    return Success(mapper(this.data))
}