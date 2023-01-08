package com.example.thindie.wantmoex.data.network.dto.history


import com.google.gson.annotations.SerializedName

data class RawHistoryDTO(
    @SerializedName("history")
    val history: History
)