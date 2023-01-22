package com.example.thindie.wantmoex.data.network.retrofit

import com.example.thindie.wantmoex.data.network.dto.multifull.CoinDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoins(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSym: String = CURRENCY
    ): Flow<CoinDTO>

    @GET("pricemultifull")
    suspend fun getShare(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_TSYM) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_FSYMS) fSyms: String
    ): CoinDTO


    companion object {
        private const val CURRENCY = "USD"
        private const val QUERY_PARAM_API_KEY = ""
        private const val QUERY_PARAM_FSYMS = "fsyms"
        private const val QUERY_PARAM_TSYM = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_LIMIT = "limit"

    }


}