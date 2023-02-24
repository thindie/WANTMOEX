package com.example.thindie.wantmoex.data.storage.favourites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCoinDao {

    @Query("SELECT * FROM favouriteCoinsTable  ORDER BY fromSymbol DESC")
    fun observeAllFavouriteCoins(): Flow<List<FavouriteCoinDBModel>>

    @Query("SELECT * FROM favouriteCoinsTable  ORDER BY fromSymbol DESC")
    suspend fun getAllFavouriteCoins(): List<FavouriteCoinDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCoin(coin: FavouriteCoinDBModel)

    @Query("DELETE FROM favouriteCoinsTable WHERE fromSymbol=:id")
    suspend fun deleteFavouriteCoin(id: String)
}