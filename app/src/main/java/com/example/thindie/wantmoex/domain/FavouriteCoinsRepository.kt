package com.example.thindie.wantmoex.domain

import kotlinx.coroutines.flow.Flow

interface FavouriteCoinsRepository {
    fun observeAllFavoriteCoins(): Flow<Results<List<String>>>

    suspend fun getAllFavoriteCoins(): Results<List<String>>
    suspend fun deleteFromFavoriteCoins(deleteCoins: List<String>)
    suspend fun addToFavoriteCoins(addCoins: List<String>)
}