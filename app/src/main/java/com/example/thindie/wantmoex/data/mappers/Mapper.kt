package com.example.thindie.wantmoex.data.mappers

import com.example.thindie.wantmoex.data.network.dto.CoinDTO
import com.example.thindie.wantmoex.data.network.dto.NewsParsedDTO
import com.example.thindie.wantmoex.data.storage.allCoins.CoinDBModel
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.domain.entities.News

fun CoinDBModel.map(): Coin {
    return Coin(
        this.market,
        this.fromSymbol,
        this.toSymbol,
        this.price,
        this.lastUpdate,
        this.highDay,
        this.lowDay,
        this.lastMarket,
        this.imageUrl,
    )
}

fun CoinDTO.map(): CoinDBModel {
    return CoinDBModel(
        this.market,
        this.fromSymbol,
        this.toSymbol,
        this.price.trimPrice(),
        this.lastUpdate,
        this.highDay,
        this.lowDay,
        this.lastMarket,
        this.imageUrl,
    )
}
  fun String.trimPrice() = if (this.length > 10) this.subSequence(0,8).toString() else this


fun Coin.map(): CoinDBModel {
    return CoinDBModel(
        this.market,
        this.fromSymbol,
        this.toSymbol,
        this.price,
        this.lastUpdate,
        this.highDay,
        this.lowDay,
        this.lastMarket,
        this.imageUrl,
    )
}

fun NewsParsedDTO.map(): News {
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
}