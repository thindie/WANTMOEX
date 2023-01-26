package com.example.thindie.wantmoex.data.network.dto


import com.example.thindie.wantmoex.data.network.dto.lastNews.NewsSourceInfoDTO
import com.google.gson.annotations.SerializedName

data class NewsItselfDTO(
    @SerializedName("body")
    val body: String?,
    @SerializedName("categories")
    val categories: String?,
    @SerializedName("downvotes")
    val downvotes: String?,
    @SerializedName("guid")
    val guid: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("imageurl")
    val imageurl: String?,
    @SerializedName("lang")
    val lang: String?,
    @SerializedName("published_on")
    val publishedOn: Int?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("source_info")
    val newsSourceInfoDTO: NewsSourceInfoDTO?,
    @SerializedName("tags")
    val tags: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("upvotes")
    val upvotes: String?,
    @SerializedName("url")
    val url: String?
)