package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.map
import com.example.thindie.wantmoex.data.network.dto.parseRawNewsDTOtoNewsDTO
import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService
import com.example.thindie.wantmoex.data.network.retrofit.CryptoCoinsApiService.Companion.queryBuilder
import com.example.thindie.wantmoex.domain.CryptoNewsRepository
import com.example.thindie.wantmoex.domain.Results
import com.example.thindie.wantmoex.domain.encapsulateResult
import com.example.thindie.wantmoex.domain.entities.News
import com.example.thindie.wantmoex.domain.handleErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoNewsRepositoryImpl @Inject constructor(private val cryptoCoinsApiService: CryptoCoinsApiService) :
    CryptoNewsRepository {

    override fun observeNews(selectedTags: List<String>): Flow<Results<List<News>>> {
        return flow {
            val newsList = parseRawNewsDTOtoNewsDTO(
                cryptoCoinsApiService
                    .getLastestNews(topCoinFirst = queryBuilder(selectedTags))
            ).map {
                it.map()
            }
            emit(newsList.encapsulateResult())
        }.handleErrors()
    }

    override suspend fun getNews(selectedTags: List<String>): Results<List<News>> {
        return parseRawNewsDTOtoNewsDTO(
            cryptoCoinsApiService.getLastestNews(
                topCoinFirst = queryBuilder(
                    selectedTags
                )
            )
        ).map {
            it.map()
        }.encapsulateResult()
    }

    override suspend fun getNews(): Results<List<News>> {
       return getNews(TAGS_LIST)
    }

    companion object {
        private const val BTC = "BTC"
        private const val ETH = "ETH"
        private const val XRP = "XRP"
        private const val DOGE = "DOGE"
        private const val SHIBA = "SHIBA"
        private val TAGS_LIST = listOf(BTC, ETH, DOGE, SHIBA, XRP)
    }
}