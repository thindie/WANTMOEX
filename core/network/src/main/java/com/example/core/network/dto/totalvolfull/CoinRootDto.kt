package com.example.core.network.dto.totalvolfull

import com.google.gson.annotations.SerializedName

internal data class CoinRootDto(
    @SerializedName("RAW")
    val coinFullInfoDTO: CoinFullInfoDto?,
)
