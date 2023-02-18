package com.example.thindie.wantmoex.domain.entities

data class Coin(
    val market: String,
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lastUpdate: Long,
    val highDay: String,
    val lowDay: String,
    val lastMarket: String,
    val imageUrl: String,
) {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other is Coin) {
            return this.fromSymbol == other.fromSymbol
        }
        return false
    }

    override fun hashCode(): Int {
        return 31 * fromSymbol.hashCode()
    }
}