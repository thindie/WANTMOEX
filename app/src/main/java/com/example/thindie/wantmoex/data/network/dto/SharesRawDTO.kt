package com.example.thindie.wantmoex.data.network.dto


import com.google.gson.annotations.SerializedName

data class SharesRawDTO(
    @SerializedName("securities")
    val securities: Securities
)