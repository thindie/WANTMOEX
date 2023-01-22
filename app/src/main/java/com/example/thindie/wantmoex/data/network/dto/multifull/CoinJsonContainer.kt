package com.example.thindie.wantmoex.data.network.dto.multifull


import com.google.gson.annotations.SerializedName

data class CoinJsonContainer(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo,
    @SerializedName("DISPLAY")
    val dISPLAY: DISPLAY,
    @SerializedName("RAW")
    val coinDTO: CoinDTO
)