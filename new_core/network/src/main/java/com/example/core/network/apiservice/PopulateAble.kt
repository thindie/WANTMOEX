package com.example.core.network.apiservice

import com.example.core.network.dto.CoinDto

interface PopulateAble {
    fun getPopulated(): List<CoinDto>
}
