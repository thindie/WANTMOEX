package com.example.thindie.wantmoex.data.network.dto


import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class Securities(
    @SerializedName("columns")
    val columns: List<String>,
    @SerializedName("data")
    val `data`: List<JsonArray>
)