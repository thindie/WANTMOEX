package com.example.thindie.wantmoex.data.mappers

import com.example.thindie.wantmoex.data.network.dto.CoinDTO
import com.example.thindie.wantmoex.data.network.dto.NewsItselfDTO
import com.example.thindie.wantmoex.data.storage.CoinDBModel
import com.example.thindie.wantmoex.domain.entities.Coin
import com.example.thindie.wantmoex.domain.entities.News

fun fromDBtoCoin(coinDBModel: CoinDBModel): Coin {
    return Coin(
        coinDBModel.market,
        coinDBModel.fromSymbol,
        coinDBModel.toSymbol,
        coinDBModel.price,
        coinDBModel.lastUpdate,
        coinDBModel.highDay,
        coinDBModel.lowDay,
        coinDBModel.lastMarket,
        coinDBModel.imageUrl,
    )
}

fun fromDTOtoCoinDBModel(coinDTO: CoinDTO): CoinDBModel {

    return CoinDBModel(
        coinDTO.market,
        coinDTO.fromSymbol,
        coinDTO.toSymbol,
        coinDTO.price,
        coinDTO.lastUpdate,
        coinDTO.highDay,
        coinDTO.lowDay,
        coinDTO.lastMarket,
        coinDTO.imageUrl,
    )
}

fun fromNewsItselfDTOtoNews(newsItselfDTO: NewsItselfDTO): News {
    return News(
        newsItselfDTO.body ?: "",
        newsItselfDTO.categories ?: "",
        newsItselfDTO.downvotes ?: "",
        newsItselfDTO.guid ?: "",
        newsItselfDTO.id ?: "",
        newsItselfDTO.imageurl ?: "",
        newsItselfDTO.lang ?: "",
        newsItselfDTO.publishedOn ?: 0,
        newsItselfDTO.source ?: "",
        newsItselfDTO.tags ?: "",
        newsItselfDTO.title ?: "",
        newsItselfDTO.upvotes ?: "",
        newsItselfDTO.url ?: "",
    )
}