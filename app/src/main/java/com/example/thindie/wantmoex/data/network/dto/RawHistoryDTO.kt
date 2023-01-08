package com.example.thindie.wantmoex.data.network.dto


import com.google.gson.annotations.SerializedName

data class RawHistoryDTO(
    @SerializedName("history")
    val history: History
)