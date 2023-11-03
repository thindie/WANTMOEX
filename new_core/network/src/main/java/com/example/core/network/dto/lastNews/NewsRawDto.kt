package com.example.core.network.dto.lastNews

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class NewsRawDto(
    @SerializedName("Data")
    val jsonArray: JsonArray?,
)
