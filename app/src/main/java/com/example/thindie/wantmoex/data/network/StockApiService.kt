package com.example.thindie.wantmoex.data.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApiService {

    @GET("engines/stock/markets/shares/boardgroups/57/securities")
    suspend fun getTotalShares(
        @Query(QUERY_PARAM_ISS_DP) issDp: String = "comma",
        @Query(QUERY_PARAM_ISS_META) issMeta: String = "off",
        @Query(QUERY_PARAM_ISS_ONLY) securities: String = "securities",
        @Query(QUERY_PARAM_SECURITIES_COLUMNS) securitiesColumns: String = COLUMNS
    ) : Flow<String>



    companion object {
        private const val QUERY_PARAM_ISS_DP = "iss.dp"
        private const val QUERY_PARAM_ISS_META = "off"
        private const val QUERY_PARAM_ISS_ONLY = "iss.only"
        private const val QUERY_PARAM_SECURITIES_COLUMNS = "securities.columns"
        private const val COLUMNS =  "SECID,SHORTNAME,MARKETCODE,PREVPRICE,BOARDID"
        private const val CURRENCY = "USD"
    }


}