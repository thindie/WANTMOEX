package com.example.thindie.wantmoex.data.storage

import com.example.thindie.wantmoex.data.storage.favourites.FavouriteCoinDBModel
import com.example.thindie.wantmoex.data.storage.favourites.FavouriteCoinDao
import com.example.thindie.wantmoex.domain.FavouriteCoinsRepository
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.Results.Error
import com.example.thindie.wantmoex.domain.Results.Success
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
            Success(favoriteDao.observeAllFavouriteCoins())
        }
    }

    override suspend fun getAllFavoriteCoins(): Results<List<String>> {
        return try {
            Success(favoriteDao.getAllFavouriteCoins().map { it.fromSymbol })
        }catch (e :Exception){
            Error(e)
        }
    }


    override suspend fun deleteFromFavoriteCoins(deleteCoins: List<String>) {
        val idList = mutableListOf<Int>()
        deleteCoins.forEach { id ->
            favoriteDao.getAllFavouriteCoins().forEach { coin ->
                if (coin.fromSymbol == id) {
                    idList.add(coin.id)
                }
            }
        }
        idList.forEach {
            favoriteDao.deleteFavouriteCoin(it)
        }
    }

    override suspend fun addToFavoriteCoins(addCoins: List<String>) {
        addCoins.forEach {
            favoriteDao.insertFavouriteCoin(FavouriteCoinDBModel(fromSymbol = it))
        }
    }

}