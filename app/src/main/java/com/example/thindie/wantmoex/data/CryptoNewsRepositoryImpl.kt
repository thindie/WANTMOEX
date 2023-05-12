package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.map
import com.example.core.network.dto.parseRawNewsDTOtoNewsDTO
import com.example.core.network.retrofit.CryptoCoinsApiService
import com.example.core.network.retrofit.CryptoCoinsApiService.Companion.queryBuilder
import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.encapsulateResult
import com.example.thindie.wantmoex.domain.entities.News
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoNewsRepositoryImpl @Inject constructor(private val cryptoCoinsApiService: com.example.core.network.retrofit.CryptoCoinsApiService) :
    CryptoNewsRepository {


    override suspend fun getNews(selectedTags: List<String>): Results<List<News>> {
        return try {
            com.example.core.network.dto.parseRawNewsDTOtoNewsDTO(
                cryptoCoinsApiService.getLastestNews(topCoinFirst = queryBuilder(selectedTags))
            ).map {
                it.map()
            }.encapsulateResult()
        } catch (e: Exception) {
            return Results.Error(e)
        }
    }
}