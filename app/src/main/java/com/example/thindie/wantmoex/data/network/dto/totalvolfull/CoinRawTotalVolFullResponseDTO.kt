package com.example.thindie.wantmoex.data.network.dto.totalvolfull


import com.google.gson.annotations.SerializedName

data class CoinRawTotalVolFullResponseDTO(
    @SerializedName("NewsItselfDTO")
    val jsonData: List<CoinRootDTO>?,
)