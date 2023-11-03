package com.example.core.network.apiservice

import com.example.core.network.dto.lastNews.NewsRawDto
import com.example.core.network.dto.multifull.CoinRawMultiFullResponseDto
import com.example.core.network.dto.totalvolfull.CoinRawTotalVolFullResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoins(
        @Query(QUERY_PARAM_COINS_LIST_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_CURRENCY) currency: String = CURRENCY,
    ): Response<CoinRawTotalVolFullResponseDto>

    @GET("pricemultifull")
    suspend fun getCoin(
        @Query(QUERY_PARAM_TO_CURRENCIES) currency: String = CURRENCY,
        @Query(QUERY_PARAM_COIN_TICKER, encoded = true) tickers: String,
    ): Response<CoinRawMultiFullResponseDto>

    @GET("v2/news/")
    suspend fun getLastestNews(
        @Query(QUERY_PARAM_NEWS_TAGS_CATEGORIES, encoded = true) topCoinFirst: String,
        @Query(QUERY_PARAM_LANGUAGE) lang: String = LANGUAGE,
    ): Response<NewsRawDto>

    companion object {

        const val CURRENCY = "USD"

        // NEWS
        private const val LANGUAGE = "EN"
        private const val QUERY_PARAM_LANGUAGE = "lang"
        private const val QUERY_PARAM_NEWS_TAGS_CATEGORIES = "categories"

        // COINS
        private const val QUERY_PARAM_COIN_TICKER = "fsyms"
        private const val QUERY_PARAM_TO_CURRENCY = "tsym"
        private const val QUERY_PARAM_TO_CURRENCIES = "tsyms"
        private const val QUERY_PARAM_COINS_LIST_LIMIT = "limit"
        const val BASE_URL_FOR_COIN_IMAGE = "https://cryptocompare.com/"
    }
}
