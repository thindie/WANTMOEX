package com.example.thindie.wantmoex.presentation

import com.example.thindie.wantmoex.domain.entities.Coin

class CoinUIModel(
    val market: String,
    val fromSymbol: String,
    val toSymbol: String,
    val price: String,
    val lastUpdate: Long,
    val highDay: String,
    val lowDay: String,
    val lastMarket: String,
    val imageUrl: String,
    val isFavorite: Boolean
)


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
        isFavorite = false

    )
}

val fromCoinToUIDeep: (Coin, Boolean) -> CoinUIModel = { domainModel: Coin, isIt: Boolean ->
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
        isFavorite = isIt
    )
}