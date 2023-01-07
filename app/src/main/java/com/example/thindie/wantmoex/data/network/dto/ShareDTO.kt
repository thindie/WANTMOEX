package com.example.thindie.wantmoex.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ShareDTO @Inject constructor(
    @SerializedName("SECID")
    @Expose
    val id: String,
    @SerializedName("SHORTNAME")
    @Expose
    val shortName: String,
    @SerializedName("MARKETCODE")
    @Expose
    val marketCode: String,
    @SerializedName("PREVPRICE")
    @Expose
    val prevPrice: String,
    @SerializedName("BOARDID")
    @Expose
    val boardId: String,
)