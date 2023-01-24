package com.example.thindie.wantmoex.data.network.retrofit

import com.example.thindie.wantmoex.data.network.dto.multifull.CoinRawMultiFullResponseDTO
import com.example.thindie.wantmoex.data.network.dto.totalvolfull.CoinRawTotalVolFullResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {

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


    companion object {
        const val CURRENCY = "USD"
        private const val QUERY_PARAM_API_KEY = ""
        private const val QUERY_PARAM_FSYMS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_LIMIT = "limit"
    }


}