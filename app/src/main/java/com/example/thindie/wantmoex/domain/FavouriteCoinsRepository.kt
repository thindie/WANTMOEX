package com.example.thindie.wantmoex.domain

import kotlinx.coroutines.flow.Flow

interface FavouriteCoinsRepository {
    fun observeAllFavoriteCoins(): Flow<Results<List<String>>>

    suspend fun checkIsFavorite(id: String): Boolean
    suspend fun getAllFavoriteCoins(): Results<List<String>>
    suspend fun deleteFromFavoriteCoins(id: String)
    suspend fun addToFavoriteCoins(id: String)
}