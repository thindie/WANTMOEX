package com.example.core.domain.domaincoinslist.entity

data class Coin(
    val market: String,
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lastUpdate: Long,
    val openDay: String,
    val highDay: String,
    val lowDay: String,
    val lastMarket: String,
    val imageUrl: String,
    val isGrowing: Boolean,
    val percentDelta: String,
    val isFavorite: Boolean = false,
) : Comparable<Coin> {
    override fun compareTo(other: Coin): Int {
        return fromSymbol.compareTo(other.fromSymbol)
    }
}
