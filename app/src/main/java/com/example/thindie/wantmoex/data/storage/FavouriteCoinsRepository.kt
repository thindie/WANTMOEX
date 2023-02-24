package com.example.thindie.wantmoex.data.storage

import com.example.thindie.wantmoex.domain.Results
import kotlinx.coroutines.flow.Flow

interface FavouriteCoinsRepository {
    fun observeAllFavoriteCoins(): Flow<Results<List<String>>>

    suspend fun getAllFavoriteCoins(): Results<List<String>>
    suspend fun deleteFromFavoriteCoins(id: String)
    suspend fun addToFavoriteCoins(id: String)
}