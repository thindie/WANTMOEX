package com.example.thindie.wantmoex.data.storage

import com.example.thindie.wantmoex.data.storage.allCoins.CoinDBModel
import com.example.thindie.wantmoex.data.storage.allCoins.CoinDao
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.Results.Error
import com.example.thindie.wantmoex.domain.Results.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalCoinRepositoryImpl @Inject constructor(
    private val local: CoinDao,
) : LocalCoinRepository {

    override fun observeAllCoins(): Flow<Results<List<CoinDBModel>>> {
        return try {
            flow { Success(local.observeAllCoins()) }
        } catch (e: Exception) {
            flow { Results.Error(e) }
        }
    }

    override fun observeAllCoins(limit: Int): Flow<Results<List<CoinDBModel>>> {
        return observeAllCoins()
    }

    override fun observeCoin(fromSymbol: String): Flow<Results<CoinDBModel>> {
        return try {
            flow { Success(local.getCoin(fromSymbol)) }
        } catch (e: Exception) {
            flow { Results.Error(e) }
        }
    }

    override suspend fun getCoin(fromSymbol: String): Results<CoinDBModel> {
        return try {
            Success(local.getCoin(fromSymbol))
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getAllCoins(): Results<List<CoinDBModel>> {
         return try {
             Success(local.getAllCoins())
         }catch (e : Exception){
             Error(e)
         }
    }

    override suspend fun getAllCoins(limit: Int): Results<List<CoinDBModel>> {
       return  getAllCoins()
    }

}