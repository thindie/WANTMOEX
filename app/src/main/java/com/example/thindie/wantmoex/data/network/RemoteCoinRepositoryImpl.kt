package com.example.thindie.wantmoex.data.network

import com.example.thindie.wantmoex.data.network.dto.CoinDTO
import com.example.thindie.wantmoex.data.network.dto.toCoinDTO
import com.example.thindie.wantmoex.data.network.dto.toCoinListDTO
import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.encapsulateResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCoinRepositoryImpl @Inject constructor(
    private val remote: CryptoCoinsApiService,
) :
    RemoteCoinRepository {

    override fun observeAllCoins(): Flow<Results<List<CoinDTO>>> {
        return observeAllCoins(LIMIT)
    }

    override fun observeAllCoins(limit: Int): Flow<Results<List<CoinDTO>>> {
        return flow { emit(getAllCoins(limit)) }
    }

    override fun observeCoin(fromSymbol: String): Flow<Results<CoinDTO>> {
        return flow {
            emit(getCoin(fromSymbol))
        }
    }

    override suspend fun getCoin(fromSymbol: String): Results<CoinDTO> {
        return knockNet {
            remote.getCoin(fSyms = fromSymbol).toCoinDTO()?.encapsulateResult()!!
        }
    }

    override suspend fun getAllCoins(): Results<List<CoinDTO>> = getAllCoins(LIMIT)

    override suspend fun getAllCoins(limit: Int): Results<List<CoinDTO>> {
        return knockNet {
            remote.getTopCoins(limit = limit).toCoinListDTO()
                ?.encapsulateResult()!!
        }
    }

    private inline fun <T : Results<*>> knockNet(t: () -> T): T {
        return try {
            t.invoke()
        } catch (e: Exception) {
            return Results.Error(e) as T
        }
    }

    companion object {
        private const val LIMIT = 10
    }
}