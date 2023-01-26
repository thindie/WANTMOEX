package com.example.thindie.wantmoex.data.network.dto.lastNews


import com.google.gson.annotations.SerializedName

data class NewsSourceInfoDTO(
    @SerializedName("img")
    val sourceInfoImg: String?,
    @SerializedName("lang")
    val sourceInfoLang: String?,
    @SerializedName("name")
    val sourceInfoName: String?
)