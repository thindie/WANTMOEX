package com.example.thindie.wantmoex.data.network.dto


import com.example.thindie.wantmoex.data.network.dto.multifull.CoinRawMultiFullResponseDTO
import com.example.thindie.wantmoex.data.network.dto.totalvolfull.CoinFullInfoDTO
import com.example.thindie.wantmoex.data.network.dto.totalvolfull.CoinRawTotalVolFullResponseDTO
import com.example.thindie.wantmoex.data.network.retrofit.RetrofitFactory
import com.google.gson.Gson
import com.google.gson.JsonObject


/**
*  Список содержит в себе наименования Криптовалют. наполняется в fromTotalVolFullToDTOList,
 *  при первичном сетевом запросе. необходим для fromMultiFullToDTO
 *
*/
private val allCoinsNameContainer = mutableListOf<String>()

/**
* Возращает лист, содержащий в себе модели CoinDTO. Количество моделей ограничено параметром limit
 * сетевом запросе /GET totalVolFull.
*/
fun fromTotalVolFullToDTOList(thisComeFromNetwork: CoinRawTotalVolFullResponseDTO): List<CoinDTO> {
    val topCoinList: MutableList<CoinDTO> = mutableListOf()

    thisComeFromNetwork.jsonData?.map { coinRootJson ->
        val coinFullInfo = coinRootJson.coinFullInfoDTO ?: return topCoinList
        val dtoToAdd = getCoinDTOFromCoinFullInfo(coinFullInfo)
        topCoinList.add(dtoToAdd)
        val coinName = dtoToAdd.fromSymbol
        if(!allCoinsNameContainer.contains(coinName)){
                allCoinsNameContainer.add(coinName)
        }
    }
    return topCoinList.toList()
}

/**
 * Возвращает лист, содержащий в себе на данном этапе реализации модель CoinDTO за индексом "ноль"
 * сетевой запрос /GET multiFull.
 */
fun fromMultiFullToDTO(thisComeFromNetwork: CoinRawMultiFullResponseDTO): List<CoinDTO> {
    val resultList: MutableList<CoinDTO> = mutableListOf()

    allCoinsNameContainer.forEach { alreadyKnownCoinName ->
        val knownJsonObject = thisComeFromNetwork.jsonObject ?: return resultList

        val keySetOfKnownObject = knownJsonObject.keySet()
        for (eachKey in keySetOfKnownObject) {
            if (eachKey == alreadyKnownCoinName) {
                val revealDeepJsonObject: JsonObject? =
                    knownJsonObject.getAsJsonObject(alreadyKnownCoinName)
                val coinFullInfoDTO = Gson().fromJson(revealDeepJsonObject, CoinFullInfoDTO::class.java)
                resultList.add(getCoinDTOFromCoinFullInfo(coinFullInfoDTO))
            }
        }
    }
    return resultList.toList()
}



private fun getCoinDTOFromCoinFullInfo(coinFullInfoDTO: CoinFullInfoDTO): CoinDTO {
    val rawCoinDto = Gson().fromJson(coinFullInfoDTO.jsonObject, CoinDTO::class.java)
    val imgUrlTail = rawCoinDto.imageUrl
    return rawCoinDto.copy(imageUrl = RetrofitFactory.BASE_IMAGE_URL.plus(imgUrlTail))
}


