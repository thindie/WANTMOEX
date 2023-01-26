package com.example.thindie.wantmoex.data.network.dto.lastNews


import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class NewsRawDTO(
    @SerializedName("Data")
    val jsonArray: JsonArray?
)
