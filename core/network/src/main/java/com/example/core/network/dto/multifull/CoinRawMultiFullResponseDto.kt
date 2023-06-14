package com.example.core.network.dto.multifull

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

internal data class CoinRawMultiFullResponseDto(
    @SerializedName("RAW")
    val jsonObject: JsonObject?,
)
