package com.example.core.network.dto.totalvolfull

import com.google.gson.annotations.SerializedName

data class CoinRawTotalVolFullResponseDto(
    @SerializedName("Data")
    val jsonData: List<CoinRootDto>?,
)
