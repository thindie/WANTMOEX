package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.storage.favourites.FavouriteCoinDao
import com.example.thindie.wantmoex.di.DispatchersModule
import com.example.thindie.wantmoex.domain.FavouriteCoinsRepository
import com.example.thindie.wantmoex.domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoFavoritesRepositoryImpl @Inject constructor(
    private val favouriteCoinsDataBase: FavouriteCoinDao,
    @DispatchersModule.IODispatcher private val IODispatcher: CoroutineDispatcher,
) : FavouriteCoinsRepository {

    override fun observeAllFavoriteCoins(): Flow<Result<List<String>>> {
        TODO("Not yet implemented")
    }

    override fun getAllFavoriteCoins(): Result<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavoriteCoins(deleteCoins: List<String>) {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavoriteCoins(addCoins: List<String>) {
        TODO("Not yet implemented")
    }

}