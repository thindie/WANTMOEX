package com.example.core.network.apiservice.onusdcurrency

import com.example.core.network.apiservice.oneurocurrency.Rating

data class CoinInfo(
    val Algorithm: String,
    val AssetLaunchDate: String,
    val BlockNumber: String,
    val BlockReward: String,
    val BlockTime: String,
    val DocumentType: String,
    val FullName: String,
    val Id: String,
    val ImageUrl: String,
    val Internal: String,
    val MaxSupply: String,
    val Name: String,
    val NetHashesPerSecond: String,
    val ProofType: String,
    val Rating: Rating,
    val Type: String,
    val Url: String,
)