package com.example.core.network.dto

import android.util.Log
import com.example.core.network.apiservice.ApiService.Companion.BASE_URL_FOR_COIN_IMAGE
import com.example.core.network.dto.multifull.CoinRawMultiFullResponseDto
import com.example.core.network.dto.totalvolfull.CoinFullInfoDto
import com.example.core.network.dto.totalvolfull.CoinRawTotalVolFullResponseDto
import com.google.gson.Gson
import com.google.gson.JsonObject

/**
 * Возращает лист, содержащий в себе модели CoinDto. Количество моделей ограничено параметром limit в
 * сетевом запросе /GET totalVolFull.
 */
internal fun fromTotalVolFullToDTOList(thisComeFromNetwork: CoinRawTotalVolFullResponseDto): List<CoinDto> {
    return try {
        checkNotNull(thisComeFromNetwork.jsonData) {
            "where: 'fromTotalVolFullToDTOList' " +
                    "what:   from network data is null " +
                    "result: returned emptyList of dto "
        }.mapNotNull { coinRootJson ->
            try {
                val fullInfoDto = checkNotNull(coinRootJson.coinFullInfoDTO)
                getCoinDTOFromCoinFullInfo(fullInfoDto)
            } catch (e: NullPointerException) { // todo( IllegalState or smth else
                null
            }
        }
    } catch (e: IllegalStateException) {
        emptyList()
    }
}

/**
 *
 *Transforms a [CoinRawMultiFullResponseDto] from network into a list of [CoinDto] objects.
 *This function parses the JSON data received from the network and extracts individual
 *[CoinFullInfoDto] objects from it. Then, it converts those objects into [CoinDto] objects
 *using the [getCoinDTOFromCoinFullInfo] function.
 *@param thisComeFromNetwork the [CoinRawMultiFullResponseDto] object received from the network
 *@return a list of [CoinDto] objects extracted from the JSON data or  emptyList()
 *@see CoinRawMultiFullResponseDto
 *@see CoinFullInfoDto
 *@see getCoinDTOFromCoinFullInfo */
internal fun fromMultiFullToDTO(thisComeFromNetwork: CoinRawMultiFullResponseDto): List<CoinDto> {
    val knownJsonObject = try {
        checkNotNull(thisComeFromNetwork.jsonObject) {
            "where: 'fromMultiFullToDTO' " +
                    "what:   from network data is null " +
                    "result: returned emptyList of dto "
        }
    } catch (e: IllegalStateException) {
        return emptyList()
    }
    return knownJsonObject
        .entrySet().mapNotNull { MapEntry ->
            try {
                val detectedTagTOParseJson = MapEntry.key.toString()
                val rootJsonObject: JsonObject =
                    knownJsonObject.getAsJsonObject(detectedTagTOParseJson)
                val dtoFullInfo = Gson()
                    .fromJson(rootJsonObject, CoinFullInfoDto::class.java)
                getCoinDTOFromCoinFullInfo(dtoFullInfo)
            } catch (e: NullPointerException) {
                Log.e(
                    "SERVICE_TAG",
                    "error detected in $MapEntry; will be skipped "
                ); null
            }
        }
}

private fun getCoinDTOFromCoinFullInfo(coinFullInfoDTO: CoinFullInfoDto): CoinDto {
    val rawCoinDto = Gson().fromJson(coinFullInfoDTO.jsonObject, CoinDto::class.java)
    val imgUrlTail = rawCoinDto.imageUrl
    return rawCoinDto.copy(imageUrl = BASE_URL_FOR_COIN_IMAGE.plus(imgUrlTail))
}

internal fun CoinRawTotalVolFullResponseDto?.toCoinListDTO(): List<CoinDto>? {
    return this?.let { fromTotalVolFullToDTOList(it) }
}

internal fun CoinRawMultiFullResponseDto?.rawToCoinDto(): List<CoinDto>? {
    return this?.let { fromMultiFullToDTO(it) }
}
