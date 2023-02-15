package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.network.RemoteCoinRepository
import com.example.thindie.wantmoex.data.storage.CryptoFavoritesRepositoryImpl
import com.example.thindie.wantmoex.data.storage.LocalCoinRepository
import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.CryptoCoinRepository
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCoinsRepositoryImpl @Inject constructor(
    private val remoteCoinRepository: RemoteCoinRepository,
    private val localCoinRepository: LocalCoinRepository,
    private val favoritesRepositoryImpl: CryptoFavoritesRepositoryImpl,
    @DispatchersModule.IODispatcher private val IODispatcher: CoroutineDispatcher,
) : CryptoCoinRepository {


    override fun observeAllCoins(): Flow<Results<List<Coin>>> {
      return observeAllCoins(UNSETTED_LIMIT)
    }

    override fun observeAllCoins(limit: Int): Flow<Results<List<Coin>>> {
        TODO()
    }

    override fun observeCoin(fromSymbol: String): Flow<Results<Coin>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoin(fromSymbol: String): Results<Coin> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCoins(): Results<List<Coin>> {
        return getAllCoins(UNSETTED_LIMIT)
    }

    override suspend fun getAllCoins(limit: Int): Results<List<Coin>> {
        TODO("Not yet implemented")
    }
    companion object{
        private const val UNSETTED_LIMIT = 10
    }
}