package com.example.core.data.coin.mapper

import com.example.core.data.mappingutils.trimPrice
import com.example.core.database.instance.CoinDbModel
import com.example.core.domain.domaincoin.entity.Coin


fun CoinDbModel.coinDbModelToCoin(): Coin {
    return Coin(
        market = this.market,
        fromSymbol = this.fromSymbol,
        toSymbol = this.toSymbol,
        price = this.price.trimPrice(),
        lastUpdate = this.lastUpdate,
        openDay = this.openDay,
        highDay = this.highDay,
        lowDay = this.lowDay,
        lastMarket = this.lastMarket,
        imageUrl = this.imageUrl,
        isGrowing = isGrowing,
        percentDelta = percentDelta
    )
}
