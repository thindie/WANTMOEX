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
        var result = market.hashCode()
        result = 31 * result + fromSymbol.hashCode()
        result = 31 * result + toSymbol.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + lastUpdate.hashCode()
        result = 31 * result + highDay.hashCode()
        result = 31 * result + lowDay.hashCode()
        result = 31 * result + lastMarket.hashCode()
        result = 31 * result + imageUrl.hashCode()
        return result
    }
}