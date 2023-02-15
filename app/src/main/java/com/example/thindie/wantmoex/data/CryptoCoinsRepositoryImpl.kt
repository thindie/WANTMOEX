package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService
import com.example.thindie.wantmoex.data.storage.CoinDao
import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.Result
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCoinsRepositoryImpl @Inject constructor(
    private val cryptoCoinsApiService: CryptoCoinsApiService,
    private val appDataBase: CoinDao,
    @DispatchersModule.IODispatcher private val IODispatcher: CoroutineDispatcher,
) : CryptoCoinRepository {


    override fun observeAllCoins(): Flow<Result<List<Coin>>> {
        TODO("Not yet implemented")
    }

    override fun observeAllCoins(limit: Int): Flow<Result<List<Coin>>> {
        TODO("Not yet implemented")
    }

    override fun observeCoin(fromSymbol: String): Flow<Result<Coin>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoin(fromSymbol: String): Result<Coin> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCoins(): Result<List<Coin>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCoins(limit: Int): Result<List<Coin>> {
        TODO("Not yet implemented")
    }
}