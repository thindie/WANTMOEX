package com.example.thindie.wantmoex.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val BASE_URL = "https://iss.moex.com/iss/"
    const val BASE_IMAGE_URL = "https://s3-symbol-logo.tradingview.com/%s--big.svg"


    val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(RequestInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: StockApiService = retrofit.create(StockApiService::class.java)
}