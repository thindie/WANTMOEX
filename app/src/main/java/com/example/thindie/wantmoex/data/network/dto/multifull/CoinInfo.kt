package com.example.thindie.wantmoex.data.network.dto.multifull


import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("FullName")
    val fullName: String,
    @SerializedName("Id")
    val id: String,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    @SerializedName("Name")
    val name: String
)