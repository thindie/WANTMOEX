package com.example.thindie.wantmoex.data.storage.favourites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteCoinDao {

    @Query("SELECT * FROM favouriteCoinsTable  ORDER BY fromSymbol DESC")
    suspend fun getAllFavouriteCoins(): List<FavouriteCoinDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCoin(coin : FavouriteCoinDBModel)

    @Query("DELETE FROM favouriteCoinsTable WHERE id=:favouriteCoinID")
    fun deleteFavouriteCoin(favouriteCoinID : Int)
}