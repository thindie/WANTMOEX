package com.example.core.network.response.totalvolumeresponse

data class TotalCoinsVolumeResponse(
    val Data: List<Data>,
    val HasWarning: Boolean,
    val Message: String,
    val MetaData: MetaData,
    val RateLimit: RateLimit,
    val SponsoredData: List<Any>,
    val Type: Int
)
