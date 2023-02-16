package com.example.thindie.wantmoex.presentation

import com.example.thindie.wantmoex.domain.entities.Coin

data class CoinUIModel(
    val market: String,
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lastUpdate: Long,
    val highDay: String,
    val lowDay: String,
    val lastMarket: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val isShowExpand: Boolean,
)

fun CoinUIModel.expandChange(): CoinUIModel {
    return this.copy(isShowExpand = !isShowExpand)
}

  fun  CoinUIModel.isFavorite(detector: () -> Boolean): CoinUIModel{
    return  this.copy(isFavorite = detector())
}

val fromCoinToUILazy: (Coin) -> CoinUIModel = { domainModel: Coin ->
    CoinUIModel(
        domainModel.market,
        domainModel.fromSymbol,
        domainModel.toSymbol,
        domainModel.price,
        domainModel.lastUpdate,
        domainModel.highDay,
        domainModel.lowDay,
        domainModel.lastMarket,
        domainModel.imageUrl,
        isFavorite = false,
        isShowExpand = false

    )
}

val fromCoinToUIDeep: (Boolean, Coin) -> CoinUIModel = { isIt: Boolean, domainModel: Coin ->
    CoinUIModel(
        domainModel.market,
        domainModel.fromSymbol,
        domainModel.toSymbol,
        domainModel.price,
        domainModel.lastUpdate,
        domainModel.highDay,
        domainModel.lowDay,
        domainModel.lastMarket,
        domainModel.imageUrl,
        isFavorite = isIt,
        isShowExpand = true
    )
}