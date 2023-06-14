package com.example.core.data.mappingutils

private const val STRING_PRICE_LENGTH = 7

fun String.trimPrice() =
    if (this.length > STRING_PRICE_LENGTH) {
        this.subSequence(0, STRING_PRICE_LENGTH)
            .toString()
    } else {
        this
    }

private const val PATTERN = "%s%s"
private const val PERCENTAGE_PLUS = "+"
private const val PERCENTAGE_MINUS = "-"
private const val PERCENT = "%"
private const val ON_PERCENTS = 100
private const val PERCENT_STRING_ = 0
private const val RANGE = 3

//
/**
 * 'this[Boolean]' as  CoinDbModel-based field 'isGrowing'
 *  [currentPrice] and [openPrice] also CoinDbModel-based fields values
 *  algorithm:
 *  detect case Model [isGrowing] or not, build [String.Format] based on
 *  [PATTERN], string-symbol [PERCENTAGE_PLUS] or [PERCENTAGE_MINUS],
 *  and expressions: [growingExpression] or [fallingExpression]
 * * */
fun Boolean.percentDelta(currentPrice: Double, openPrice: Double): String {
    fun Double.asPercentageString() = this.toString().subSequence(PERCENT_STRING_, RANGE).toString()
    val growingExpression =
        ((currentPrice - openPrice).div(openPrice).times(ON_PERCENTS)).asPercentageString()
    val fallingExpression =
        (openPrice - currentPrice).div(openPrice).times(ON_PERCENTS).asPercentageString()
    return if (this /*isGrowing*/) {
        String.format(
            PATTERN, /* string percentage pattern  */
            PERCENTAGE_PLUS, /* 'growing' */
            growingExpression
        )
            .plus(PERCENT)
    } else { /*isFalling*/
        String.format(
            PATTERN,
            PERCENTAGE_MINUS, /* 'falling' */
            fallingExpression
        )
            .plus(PERCENT)
    }
}
