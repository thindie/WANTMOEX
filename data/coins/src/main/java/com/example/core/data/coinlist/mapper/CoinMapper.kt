package com.example.core.data.coinlist.mapper

import com.example.core.network.dto.CoinDto
import com.example.thindie.domain.entities.Coin

internal fun CoinDto.coinDtoToCoin() =
    Coin(
        type = type,
        market = market,
        fromSymbol = fromSymbol,
        toSymbol = toSymbol,
        flags = flags,
        price = price,
        lastUpdate = lastUpdate,
        lastVolume = lastVolume,
        lastVolumeTo = lastVolumeTo,
        lastTradeId = lastTradeId,
        volumeDay = volumeDay,
        volumeDayTo = volumeDayTo,
        volume24Hour = volume24Hour,
        volume24HourTo = volume24HourTo,
        openDay = openDay,
        highDay = highDay,
        lowDay = lowDay,
        open24Hour = open24Hour,
        high24Hour = high24Hour,
        low24Hour = low24Hour,
        lastMarket = lastMarket,
        volumeHour = volumeHour,
        volumeHourTo = volumeHourTo,
        openHour = openHour,
        highHour = highHour,
        lowHour = lowHour,
        topTierVolume24Hour = topTierVolume24Hour,
        topTierVolume24HourTo = topTierVolume24HourTo,
        change24Hour = change24Hour,
        changePCT24Hour = changePCT24Hour,
        changeDay = changeDay,
        changePCTDay = changePCTDay,
        supply = supply,
        mktCap = mktCap,
        totalVolume24Hour = totalVolume24Hour,
        totalVolume24HourTo = totalVolume24HourTo,
        totalTopTierVolume24Hour = totalTopTierVolume24Hour,
        totalTopTierVolume24HourTo = totalTopTierVolume24HourTo,
        imageUrl = imageUrl
    )
