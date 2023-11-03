package com.example.core.network.dto.totalvolfull


import com.example.core.network.apiservice.ApiService.Companion.CURRENCY
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class CoinFullInfoDto(
    @SerializedName(CURRENCY)
    @Expose
    val jsonObject: JsonObject?,
)
