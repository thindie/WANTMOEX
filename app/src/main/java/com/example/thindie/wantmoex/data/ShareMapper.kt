package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.network.dto.ShareDTO
import com.example.thindie.wantmoex.domain.entities.Share
import javax.inject.Inject

class ShareMapper @Inject constructor() {
    operator fun invoke (shareDTO: ShareDTO) : Share{
        return Share(
            id = shareDTO.id,
            shortName = shareDTO.shortName,
            prevPrice = shareDTO.prevPrice
        )
    }
}