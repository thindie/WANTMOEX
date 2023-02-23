package com.example.thindie.wantmoex.domain.entities


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
    val isGrowing: Boolean = openDay.toDouble() < price.toDouble(),
    val percentDelta: String = isGrowing.percentDelta(price.toDouble(), openDay.toDouble()),
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

private const val EXPRESSION = "%s%s"
private const val PERCENTAGE_PLUS = "+"
private const val PERCENTAGE_MINUS = "-"
private const val PERCENT = "%"
private const val ON_PERCENTS = 100
private const val PERCENT_STRING_ = 0
private const val RANGE = 3

fun Boolean.percentDelta(currentPrice: Double, openPrice: Double): String {
    fun Double.asPercentageString() = this.toString().subSequence(PERCENT_STRING_, RANGE).toString()
    return if (this) {

        String.format(
            EXPRESSION, PERCENTAGE_PLUS,
            ((currentPrice - openPrice).div(openPrice).times(ON_PERCENTS)).asPercentageString()
        )
            .plus(PERCENT)


    } else
        String.format(
            EXPRESSION, PERCENTAGE_MINUS,
            (openPrice - currentPrice).div(openPrice).times(ON_PERCENTS).asPercentageString()
        )
            .plus(PERCENT)
}


