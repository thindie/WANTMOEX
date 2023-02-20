package com.example.thindie.wantmoex.data.mappers

import com.example.thindie.wantmoex.data.network.dto.CoinDTO
import com.example.thindie.wantmoex.data.network.dto.NewsParsedDTO
import com.example.thindie.wantmoex.data.storage.allCoins.CoinDBModel
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.domain.entities.News


inline fun <reified T, R> T.entityMap(): R {
    val t: T = this
    return when (T::class) {
        CoinDBModel::class -> {
            t as CoinDBModel
            Coin(
                market = t.market,
                fromSymbol = t.fromSymbol,
                toSymbol = t.toSymbol,
                price = t.price.trimPrice(),
                lastUpdate = t.lastUpdate,
                openDay = t.openDay,
                highDay = t.highDay,
                lowDay = t.lowDay,
                lastMarket = t.lastMarket,
                imageUrl = t.imageUrl,
            ) as R
        }
        CoinDTO::class -> {
            t as CoinDTO
            CoinDBModel(
                market = t.market,
                fromSymbol = t.fromSymbol,
                toSymbol = t.toSymbol,
                price = t.price.trimPrice(),
                lastUpdate = t.lastUpdate,
                openDay = t.openDay,
                highDay = t.highDay,
                lowDay = t.lowDay,
                lastMarket = t.lastMarket,
                imageUrl = t.imageUrl,
            ) as R
        }
        else -> { throw UnsupportedOperationException("Check mapper logic")}
    }
}


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

fun CoinDTO.map(): CoinDBModel {
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

fun String.trimPrice() = if (this.length > 10) this.subSequence(0, 8).toString() else this


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