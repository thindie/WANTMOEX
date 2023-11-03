package com.example.core.data.coinlist.mapper

import com.example.core.data.mappingutils.percentDelta
import com.example.core.data.mappingutils.trimPrice
import com.example.core.database.instance.CoinDbModel
import com.example.core.domain.domaincoinslist.entity.Coin
import com.example.core.network.dto.CoinDto

fun CoinDto.toCoinDbModelWithCheckedFavoriteStatus(favorite: Boolean?): CoinDbModel {
    val isGrowing = openDay.toDouble() < price.toDouble()
    return CoinDbModel(
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
        percentDelta = isGrowing.percentDelta(price.toDouble(), openDay.toDouble()),
        isFavorite = favorite ?: false
    )
}

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
        percentDelta = percentDelta,
        isFavorite = this.isFavorite
    )
}
