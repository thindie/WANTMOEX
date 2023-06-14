package com.example.thindie.wantmoex.data.mappers

import com.example.thindie.wantmoex.data.storage.allCoins.CoinDBModel
import com.example.thindie.wantmoex.domain.entities.Coin

fun CoinDBModel.map(): Coin {
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
    )
}

/*fun com.example.core.network.dto.CoinDTO.map(): CoinDBModel {
    return CoinDBModel(
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
    )
}*/

fun String.trimPrice() = if (this.length > LENGTH) this.subSequence(0, LENGTH).toString() else this

fun Coin.map(): CoinDBModel {
    return CoinDBModel(
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
    )
}
private const val LENGTH = 7
/*fun NewsParsedDTO.map(): News {
    return News(
        this.body ?: "",
        this.categories ?: "",
        this.downvotes ?: "",
        this.guid ?: "",
        this.id ?: "",
        this.imageurl ?: "",
        this.lang ?: "",
        this.publishedOn ?: 0,
        this.source ?: "",
        this.tags ?: "",
        this.title ?: "",
        this.upvotes ?: "",
        this.url ?: "",
    )
}*/
