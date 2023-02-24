package com.example.thindie.wantmoex.data.network.dto


import com.example.thindie.wantmoex.data.network.dto.multifull.CoinRawMultiFullResponseDTO
import com.example.thindie.wantmoex.data.network.dto.totalvolfull.CoinFullInfoDTO
import com.example.thindie.wantmoex.data.network.dto.totalvolfull.CoinRawTotalVolFullResponseDTO
import com.example.thindie.wantmoex.data.network.retrofit.RetrofitFactory
import com.google.gson.Gson
import com.google.gson.JsonObject


/**
 * Возращает лист, содержащий в себе модели CoinDTO. Количество моделей ограничено параметром limit в
 * сетевом запросе /GET totalVolFull.
 */
fun fromTotalVolFullToDTOList(thisComeFromNetwork: CoinRawTotalVolFullResponseDTO): List<CoinDTO> {
    val topCoinList: MutableList<CoinDTO> = mutableListOf()
    val fromNetwork = thisComeFromNetwork.jsonData
    fromNetwork?.forEach { coinRootJson ->
        if (coinRootJson.coinFullInfoDTO != null) {
            val coinFullInfo = coinRootJson.coinFullInfoDTO
            val dtoToAdd = getCoinDTOFromCoinFullInfo(coinFullInfo)
            topCoinList.add(dtoToAdd)
        }
    }
    return topCoinList.toList()
}

/**
 * Возвращает   CoinDTO.
 * сетевой запрос /GET multiFull.
 *
 */
fun fromMultiFullToDTO(thisComeFromNetwork: CoinRawMultiFullResponseDTO): CoinDTO? {

    val knownJsonObject = thisComeFromNetwork.jsonObject ?: return null
    val detectedTagTOParseJson = knownJsonObject.entrySet().first().key.toString()
    val revealDeepJsonObject: JsonObject = knownJsonObject.getAsJsonObject(detectedTagTOParseJson)
    val coinFullInfoDTO = Gson().fromJson(revealDeepJsonObject, CoinFullInfoDTO::class.java)
    return getCoinDTOFromCoinFullInfo(coinFullInfoDTO)


}


private fun getCoinDTOFromCoinFullInfo(coinFullInfoDTO: CoinFullInfoDTO): CoinDTO {
    val rawCoinDto = Gson().fromJson(coinFullInfoDTO.jsonObject, CoinDTO::class.java)
    val imgUrlTail = rawCoinDto.imageUrl
    return rawCoinDto.copy(imageUrl = RetrofitFactory.BASE_URL_FOR_COIN_IMAGE.plus(imgUrlTail))
}

fun CoinRawTotalVolFullResponseDTO?.toCoinListDTO(): List<CoinDTO>? {
    return this?.let { fromTotalVolFullToDTOList(it) }
}

fun CoinRawMultiFullResponseDTO?.toCoinDTO(): CoinDTO? {
    return this?.let { fromMultiFullToDTO(it) }
}


