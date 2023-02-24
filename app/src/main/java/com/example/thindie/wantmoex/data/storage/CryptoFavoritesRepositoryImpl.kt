package com.example.thindie.wantmoex.data.storage

import com.example.thindie.wantmoex.data.storage.favourites.FavouriteCoinDBModel
import com.example.thindie.wantmoex.data.storage.favourites.FavouriteCoinDao
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.encapsulateResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoFavoritesRepositoryImpl @Inject constructor(
    private val favoriteDao: FavouriteCoinDao,
) : FavouriteCoinsRepository {

    override fun observeAllFavoriteCoins(): Flow<Results<List<String>>> {
        return flow {
            favoriteDao.observeAllFavouriteCoins().collect {
                val idList = mutableListOf<String>()
                it.forEach {
                    idList.add(it.fromSymbol)
                }
                emit(idList.encapsulateResult())
            }
        }

    }


    override suspend fun getAllFavoriteCoins(): Results<List<String>> {
        val list = mutableListOf<String>()
        favoriteDao.getAllFavouriteCoins().forEach { list.add(it.fromSymbol) }
        return list.encapsulateResult()
    }

    override suspend fun deleteFromFavoriteCoins(id: String) {
        favoriteDao.deleteFavouriteCoin(id)
    }

    override suspend fun addToFavoriteCoins(id: String) {
        favoriteDao.insertFavouriteCoin(FavouriteCoinDBModel(fromSymbol = id))
    }
}





