package com.example.core.network.retrofit

import com.example.core.common.commarequestbuilder.commaQueryEncodedBuilder
import com.example.core.network.apiservice.ApiService
import com.example.core.network.common.response
import com.example.core.network.dto.CoinDto
import com.example.core.network.dto.NewsDto
import com.example.core.network.dto.parseNewsDTO
import com.example.core.network.dto.rawToCoinDto
import com.example.core.network.dto.toCoinListDTO
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This entity is Because of [response]
 * dont wanna expose dependencies on other modules,
 * staying compact and dont break encapsulation
 */
@Singleton
internal class RetrofitNetwork @Inject constructor(
    okHttpClient: OkHttpClient,
) : NetworkService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    companion object {
        private const val BASE_URL = "https://min-api.cryptocompare.com/data/"
    }

    override suspend fun getCoin(ticker: String): List<CoinDto> {
        return response { apiService.getCoin(tickers = ticker) }.rawToCoinDto().orEmpty()
    }

    override suspend fun getCoins(tickerLimit: Int): List<CoinDto> {
        return response { apiService.getTopCoins(tickerLimit) }.toCoinListDTO().orEmpty()
    }

    override suspend fun getNews(newsTags: List<String>): List<NewsDto> {
        return response {
            apiService
                .getLastestNews(topCoinFirst = commaQueryEncodedBuilder(newsTags))
        }?.parseNewsDTO().orEmpty()
    }
}
