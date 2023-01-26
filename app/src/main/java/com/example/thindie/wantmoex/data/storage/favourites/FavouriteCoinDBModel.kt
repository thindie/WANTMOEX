package com.example.thindie.wantmoex.data.storage.favourites


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteCoinsTable")
data class FavouriteCoinDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fromSymbol: String,
)
