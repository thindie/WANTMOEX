package com.example.thindie.wantmoex.data.network.dto.multifull


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinRawMultiFullResponseDTO(
    @SerializedName("RAW")
    val jsonObject: JsonObject?
)