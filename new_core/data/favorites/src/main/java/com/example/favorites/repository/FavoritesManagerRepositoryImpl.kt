package com.example.favorites.repository

import com.example.core.database.instance.CoinDao
import com.example.domainfavorites.repository.FavoritesRepository
import javax.inject.Inject

internal class FavoritesManagerRepositoryImpl @Inject constructor(
    private val dao: CoinDao,
) : FavoritesRepository {

    /**
     * get from [dao] by [ticker] and update its field 'isFavorite : Boolean' by reversing it
     */
    override suspend fun changeFavoriteState(ticker: String) {
        val coinToUpdate = dao.getCoin(ticker)
        dao.upsertCoin(
            coinToUpdate.copy(isFavorite = !coinToUpdate.isFavorite)
        )
    }
}
