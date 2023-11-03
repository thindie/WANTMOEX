package com.example.core.database.instance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinDbModel(
    val market: String,
    @PrimaryKey val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lastUpdate: Long,
    val openDay: String,
    val highDay: String,
    val lowDay: String,
    val lastMarket: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val isGrowing: Boolean,
    val percentDelta: String,
)

