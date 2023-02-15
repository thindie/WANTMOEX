package com.example.thindie.wantmoex.data.network

import com.example.thindie.wantmoex.data.network.dto.CoinDTO
import com.example.thindie.wantmoex.data.network.dto.toCoinDTO
import com.example.thindie.wantmoex.data.network.dto.toCoinListDTO
import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService
import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.Results
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCoinRepositoryImpl @Inject constructor(
    private val remote: CryptoCoinsApiService,
    private val scope: CoroutineScope,
    @DispatchersModule.IODispatcher private val IODispatcher: CoroutineDispatcher,
) :
    RemoteCoinRepository {

    override fun observeAllCoins(): Flow<Results<List<CoinDTO>>> {
        return observeAllCoins(LIMIT)
    }

    override fun observeAllCoins(limit: Int): Flow<Results<List<CoinDTO>>> {
        val state: MutableStateFlow<Results<List<CoinDTO>>> = MutableStateFlow(
            Results.Success(
                emptyList()
            )
        )
        scope.launch {
            state.value = getAllCoins(limit)
        }
        return flow { state.value }
    }

    override fun observeCoin(fromSymbol: String): Flow<Results<CoinDTO>> {
        val state: MutableStateFlow<Results<CoinDTO>> =
            MutableStateFlow(Results.Error(Exception("remote coin not found")))
        scope.launch {
            state.value = getCoin(fromSymbol)
        }
        return flow { state.value }
    }

    override suspend fun getCoin(fromSymbol: String): Results<CoinDTO> = withContext(IODispatcher) {
        try {
            val coin = remote.getCoin(fSyms = fromSymbol).toCoinDTO()
            if (coin != null) {
                return@withContext Results.Success(coin)
            } else {
                return@withContext Results.Error(Exception("Coin not found!"))
            }
        } catch (e: Exception) {
            return@withContext Results.Error(e)
        }
    }

    override suspend fun getAllCoins(): Results<List<CoinDTO>> = getAllCoins(LIMIT)

    override suspend fun getAllCoins(limit: Int): Results<List<CoinDTO>> =
        withContext(IODispatcher) {
            try {
                val coins = remote.getTopCoins(limit = limit).toCoinListDTO()
                if (coins.isNullOrEmpty()) {
                    return@withContext Results.Error(Exception("remote data is absent"))
                } else {
                    return@withContext Results.Success(coins)
                }
            } catch (e: Exception) {
                return@withContext Results.Error(e)
            }
        }

    companion object {
        private const val LIMIT = 10
    }
}