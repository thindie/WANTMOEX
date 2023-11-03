package com.example.core.network.dto.totalvolfull

import com.google.gson.annotations.SerializedName

data class CoinRootDto(
    @SerializedName("RAW")
    val coinFullInfoDTO: CoinFullInfoDto?,
)
