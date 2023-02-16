package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.R
import com.example.thindie.wantmoex.data.mappers.map
import com.example.thindie.wantmoex.data.network.RemoteCoinRepository
import com.example.thindie.wantmoex.data.storage.LocalCoinRepository
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.Results.Success
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.presentation.unpackResult
import kotlinx.coroutines.flow.Flow
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
        return flow {
            remoteCoinRepository.observeAllCoins().collect { remoteResult ->

                when (remoteResult) {
                    is Success -> {
                        val webResult = remoteResult.transform {
                            it.map {
                                it.map().map()
                            }
                        }
                        emit(webResult)

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
                                    emit(dBResult)
                                }
                                is Results.Error -> {
                                    emit(Results.Error(Exception(R.string.data_error_remote.toString())))
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    override fun observeCoin(fromSymbol: String): Flow<Results<Coin>> {
        return flow {
            remoteCoinRepository.observeCoin(fromSymbol).collect { remoteCoinObserve ->
                when (remoteCoinObserve) {
                    is Success -> {
                        val remoteCoin = remoteCoinObserve.transform {
                            it.map().map()
                        }
                        emit(remoteCoin)
                    }
                    is Results.Error -> {
                        localCoinRepository.observeCoin(fromSymbol).map { localCoinObserve ->
                            when (localCoinObserve) {
                                is Success -> {
                                    val localCoin = localCoinObserve.transform {
                                        it.map()
                                    }
                                    emit(localCoin)
                                }
                                is Results.Error -> emit(Results.Error(Exception(R.string.data_error_remote.toString())))
                            }
                        }
                    }
                }
            }
        }
    }


    override suspend fun getCoin(fromSymbol: String): Results<Coin> {

        when (val remoteCoin = remoteCoinRepository.getCoin(fromSymbol)) {
            is Success -> {
                return remoteCoin.transform {
                    it.map().map()
                }
            }

            is Results.Error -> {
                return when (val localCoin = localCoinRepository.getCoin(fromSymbol)) {
                    is Success -> {
                        localCoin.transform {
                            it.map()
                        }
                    }

                    is Results.Error ->
                        Results.Error(Exception(R.string.data_error_remote.toString()))

                }
            }
            else -> {
                return Results.Error(Exception(R.string.data_error_remote.toString()))
            }
        }
    }

    override suspend fun getAllCoins(): Results<List<Coin>> {
        return getAllCoins(UNSETTED_LIMIT)
    }

    override suspend fun getAllCoins(limit: Int): Results<List<Coin>> {
        when (val remoteCoins = remoteCoinRepository.getAllCoins(limit = limit)) {
            is Success -> {
                val coins = remoteCoins.transform {
                    it.map {
                        it.map().map()
                    }
                }
                localCoinRepository.addCoins(coins.unpackResult { coinz ->
                    coinz.map {
                        it.map().map()
                    }
                })
                return coins
            }
            is Results.Error -> {
                return when (val localCoins = localCoinRepository.getAllCoins(limit)) {
                    is Success -> {
                        localCoins.transform {
                            it.map {
                                it.map()
                            }
                        }
                    }
                    is Results.Error -> {
                        Results.Error(Exception(R.string.data_error_remote.toString()))
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