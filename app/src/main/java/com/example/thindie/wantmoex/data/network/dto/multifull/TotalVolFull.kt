package com.example.thindie.wantmoex.data.network.dto.multifull


import com.google.gson.annotations.SerializedName

data class TotalVolFull(
    @SerializedName("Data")
    val dataList : List<CoinJsonContainer>
)