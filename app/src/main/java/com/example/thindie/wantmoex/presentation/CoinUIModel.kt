package com.example.thindie.wantmoex.presentation

import com.example.thindie.wantmoex.domain.entities.Coin

data class CoinUIModel(
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
    val isFavorite: Boolean,
    val isShowExpand: Boolean,
    val isGrowing: Boolean,
    val percentDelta: String,

    )


private val fromCoinToUImodel: (Boolean, Coin) -> CoinUIModel =
    { isFavorite: Boolean, domainModel: Coin ->
        CoinUIModel(
            domainModel.market,
            domainModel.fromSymbol,
            domainModel.toSymbol,
            domainModel.price,
            domainModel.lastUpdate,
            domainModel.openDay,
            domainModel.highDay,
            domainModel.lowDay,
            domainModel.lastMarket,
            domainModel.imageUrl,
            isFavorite = isFavorite,
            isShowExpand = false,
            isGrowing = domainModel.isGrowing,
            percentDelta = domainModel.percentDelta
        )
    }

fun Coin.mapToUiModel(mapper: (String) -> Boolean): CoinUIModel {
    return fromCoinToUImodel(mapper(this.fromSymbol), this)
}

