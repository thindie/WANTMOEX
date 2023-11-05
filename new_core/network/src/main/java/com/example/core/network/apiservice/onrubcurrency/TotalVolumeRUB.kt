package com.example.core.network.apiservice.onrubcurrency

import com.example.core.network.apiservice.PopulateAble
import com.example.core.network.dto.CoinDto
import com.google.gson.Gson

data class TotalVolumeRUB(
    val Data: List<Data>,
    val HasWarning: Boolean,
    val Message: String,
    val MetaData: MetaData,
    val RateLimit: RateLimit,
    val SponsoredData: List<Any>,
    val Type: Int,
) : PopulateAble {
    override fun getPopulated(): List<CoinDto> {
        return Data
            .map { data ->
                val raw = data.RAW.RUB
                val parsedRaw = Gson().toJson(raw)
                Gson().fromJson(parsedRaw, CoinDto::class.java)
            }
    }
}
