package com.example.thindie.wantmoex.data.network.retrofit

import com.example.thindie.wantmoex.data.network.dto.lastNews.NewsRawDTO
import com.example.thindie.wantmoex.data.network.dto.multifull.CoinRawMultiFullResponseDTO
import com.example.thindie.wantmoex.data.network.dto.totalvolfull.CoinRawTotalVolFullResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCoinsApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoins(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinRawTotalVolFullResponseDTO

    @GET("pricemultifull")
    suspend fun getCoin(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FSYMS) fSyms: String
    ): CoinRawMultiFullResponseDTO

    @GET("v2/news/")
    suspend fun getLastestNews(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_CATEGORIES, encoded = true) topCoinFirst: String = String.format(
            "%s${COMMA}%s${COMMA}%s${COMMA}%s${COMMA}%s", BTC,
            ETH, XRP, DOGE, SHIBA
        ),
        @Query(QUERY_PARAM_LANGUAGE) lang: String = LANGUAGE,

        ): NewsRawDTO

    companion object {

        private const val QUERY_PARAM_API_KEY = ""
        const val CURRENCY = "USD"

        //NEWS
        private const val LANGUAGE = "EN"
        private const val QUERY_PARAM_LANGUAGE = "lang"
        private const val BTC = "BTC"
        private const val COMMA = ","
        private const val ETH = "ETH"
        private const val XRP = "XRP"
        private const val DOGE = "DOGE"
        private const val SHIBA = "SHIBA"
        private const val QUERY_PARAM_CATEGORIES = "categories"

        //COINS
        private const val QUERY_PARAM_FSYMS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_LIMIT = "limit"


    }


}