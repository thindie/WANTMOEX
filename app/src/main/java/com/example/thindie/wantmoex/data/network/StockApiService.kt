package com.example.thindie.wantmoex.data.network

import com.example.thindie.wantmoex.data.network.dto.history.RawHistoryDTO
import com.example.thindie.wantmoex.data.network.dto.allShares.SharesRawDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockApiService {

    @GET(QUERY_ENDPOINT)
    suspend fun getTotalShares(
        @Query(QUERY_PARAM_ISS_DP) issDp: String = "comma",
        @Query(QUERY_PARAM_ISS_META) issMeta: String = "off",
        @Query(QUERY_PARAM_ISS_ONLY) securities: String = "securities",
        @Query(QUERY_PARAM_SECURITIES_COLUMNS) securitiesColumns: String = COLUMNS_ALL_SHARES
    ):SharesRawDTO

    @GET("history/engines/stock/markets/shares/boards/TQBR/securities/{share}.json?")
    suspend fun getShare(
        @Path("share") share: String,
        @Query(QUERY_PARAM_ISS_META) issMeta: String = "off",
        @Query(QUERY_PARAM_ISS_ONLY) history: String = "history",
        @Query(QUERY_PARAM_HISTORY_COLUMNS) historyColumns: String = COLUMNS_SINGLE_SHARE,
        @Query(QUERY_PARAM_LIMIT) limit: String = "10",
        @Query(QUERY_PARAM_FROM) from: String,
    ):RawHistoryDTO


    companion object {
        private const val QUERY_PARAM_FROM = "from"
        private const val QUERY_PARAM_ISS_DP = "iss.dp"
        private const val QUERY_PARAM_ISS_META = "iss.meta"
        private const val QUERY_PARAM_ISS_ONLY = "iss.only"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_SECURITIES_COLUMNS = "securities.columns"
        private const val QUERY_PARAM_HISTORY_COLUMNS = "history.columns"
        private const val QUERY_ENDPOINT =
            "engines/stock/markets/shares/boardgroups/57/securities.json"
        private const val COLUMNS_ALL_SHARES = "SECID,SHORTNAME,MARKETCODE,PREVPRICE,BOARDID"
        private const val COLUMNS_SINGLE_SHARE = "SECID,TRADEDATE,LEGALCLOSEPRICE"
    }


}