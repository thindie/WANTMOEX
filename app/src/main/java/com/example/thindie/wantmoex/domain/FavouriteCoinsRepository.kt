package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.Coin
import kotlinx.coroutines.flow.Flow

interface FavouriteCoinsRepository {
    suspend fun getAllFavoriteCoins(): Flow<List<Coin>>
    suspend fun deleteFromFavoriteCoins(deleteCoins: List<String>)
    suspend fun addToFavoriteCoins(addCoins: List<String>)
}