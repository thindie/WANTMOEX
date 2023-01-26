package com.example.thindie.wantmoex.domain.entities

import com.google.gson.annotations.SerializedName

data class News (
    val body: String,
    val categories: String,
    val downVotes: String,
    val guid: String,
    val id: String,
    val imageUrl: String,
    val lang: String,
    val publishedOn: Int,
    val source: String,
    val tags: String,
    val title: String,
    val upVotes: String,
    val url: String
)