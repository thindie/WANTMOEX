package com.example.thindie.wantmoex.data.mappers

import com.example.thindie.wantmoex.data.network.dto.history.RawHistoryDTO
import com.example.thindie.wantmoex.data.network.dto.allShares.ShareDTO
import com.example.thindie.wantmoex.data.network.dto.history.ShareHistoryDTO
import com.example.thindie.wantmoex.data.network.dto.allShares.SharesRawDTO
import com.example.thindie.wantmoex.domain.entities.Share
import com.google.gson.JsonArray
import javax.inject.Inject

class ShareMapper @Inject constructor() {

    private fun fromShareDtoToShare(shareDTO: ShareDTO): Share {
        return Share(
            id = shareDTO.id,
            shortName = shareDTO.shortName,
            prevPrice = shareDTO.prevPrice
        )
    }

    private fun fromShareHistoryDtoToShare(shareDTO: ShareHistoryDTO): Share {
        return Share(
            id = shareDTO.id,
            shortName = shareDTO.date,
            prevPrice = shareDTO.price
        )
    }

    fun fromRawJsonToList(rawJSON: Any): List<Share> {
        val sharesList = mutableListOf<Share>()
        when (rawJSON) {
            is SharesRawDTO -> {

                rawJSON.securities.data.forEach {
                    sharesList.add(
                        fromShareDtoToShare(
                            hardCoredMapToDTO(it)
                        )
                    )
                }
                return sharesList.toList()
            }
            is RawHistoryDTO -> {
                rawJSON.history.data.forEach {
                    sharesList.add(
                        fromShareHistoryDtoToShare(
                            hardCoredMapToHistoryDTO(it)
                        )
                    )
                }
            }
            else -> throw RuntimeException("Wrong DTO object (cast)")
        }
        return sharesList
    }

    private fun hardCoredMapToHistoryDTO(array: JsonArray): ShareHistoryDTO {
        return ShareHistoryDTO(
            array[0].asString,
            array[1].toString(),
            array[2].toString(),
        )
    }

    private fun hardCoredMapToDTO(array: JsonArray): ShareDTO {
        return ShareDTO(
            array[0].asString,
            array[1].asString,
            array[2].asString,
            array[3].toString(),
            array[4].asString,
        )
    }
}