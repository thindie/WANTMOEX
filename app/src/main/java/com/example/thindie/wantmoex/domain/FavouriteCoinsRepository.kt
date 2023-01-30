package com.example.thindie.wantmoex.domain

import kotlinx.coroutines.flow.Flow

interface FavouriteCoinsRepository {
    suspend fun getAllFavoriteCoins(): Flow<List<String>>
    suspend fun deleteFromFavoriteCoins(deleteCoins: List<String>)
    suspend fun addToFavoriteCoins(addCoins: List<String>)
}