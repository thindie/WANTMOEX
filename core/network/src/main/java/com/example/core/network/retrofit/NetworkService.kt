package com.example.core.network.retrofit

import com.example.core.network.dto.CoinDto
import com.example.core.network.dto.NewsDto

interface NetworkService {
    suspend fun getCoin(ticker: String): List<CoinDto>
    suspend fun getCoins(tickerLimit: Int): List<CoinDto>
    suspend fun getNews(newsTags: List<String>): List<NewsDto>
}
