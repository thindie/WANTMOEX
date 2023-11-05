package com.example.core.network.apiservice

import com.example.core.network.apiservice.oneurocurrency.TotalVolumeEURO
import com.example.core.network.apiservice.onjpycurrency.TotalVolumeJPY
import com.example.core.network.apiservice.onrubcurrency.TotalVolumeRUB
import com.example.core.network.apiservice.onusdcurrency.TotalVolumeUSD
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsUSD(
        @Query(QUERY_PARAM_COINS_LIST_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_CURRENCY) currency: String = CURRENCY_USD,
    ): TotalVolumeUSD

    @GET("top/totalvolfull")
    suspend fun getTopCoinsJPY(
        @Query(QUERY_PARAM_COINS_LIST_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_CURRENCY) currency: String = CURRENCY_JPY,
    ): TotalVolumeJPY

    @GET("top/totalvolfull")
    suspend fun getTopCoinsRUB(
        @Query(QUERY_PARAM_COINS_LIST_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_CURRENCY) currency: String = CURRENCY_RUB,
    ): TotalVolumeRUB

    @GET("top/totalvolfull")
    suspend fun getTopCoinsEURO(
        @Query(QUERY_PARAM_COINS_LIST_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_CURRENCY) currency: String = CURRENCY_EUR,
    ): TotalVolumeEURO

    companion object {

        private const val CURRENCY_USD = "USD"
        private const val CURRENCY_RUB = "RUB"
        private const val CURRENCY_EUR = "EUR"
        private const val CURRENCY_JPY = "JPY"

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
