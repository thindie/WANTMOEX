package com.example.thindie.wantmoex.data.network.dto.totalvolfull


import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService.Companion.CURRENCY
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinFullInfoDTO(
    @SerializedName(CURRENCY)
    @Expose
    val jsonObject: JsonObject?
)