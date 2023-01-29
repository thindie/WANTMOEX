package com.example.thindie.wantmoex.presentation.composables

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



val fromUIModelToCoin: (CoinUIModel) -> Coin = { ui: CoinUIModel ->
    Coin(
        ui.market,
        ui.fromSymbol,
        ui.toSymbol,
        ui.price,
        ui.lastUpdate,
        ui.highDay,
        ui.lowDay,
        ui.lastMarket,
        ui.imageUrl
    )
}

val fromCoinToUI : (Coin) -> CoinUIModel = { domainModel: Coin  ->
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
        isFavorite =  false

    )
}

