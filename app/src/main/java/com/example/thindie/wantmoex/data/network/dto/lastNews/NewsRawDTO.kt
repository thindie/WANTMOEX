package com.example.thindie.wantmoex.data.network.dto.lastNews


import com.example.thindie.wantmoex.data.network.dto.NewsItselfDTO
import com.google.gson.annotations.SerializedName

data class NewsRawDTO(
    @SerializedName("NewsItselfDTO")
    val newsNetWorkData: List<NewsItselfDTO?>?
)