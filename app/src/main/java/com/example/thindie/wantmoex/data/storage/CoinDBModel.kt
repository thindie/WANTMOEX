package com.example.thindie.wantmoex.data.storage


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coinsTable")
data class CoinDBModel(
    val market: String,
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lastUpdate: Long,
    val highDay: String,
    val lowDay: String,
    val lastMarket: String,
    val imageUrl: String,
)
