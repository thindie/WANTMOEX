package com.example.thindie.wantmoex.data.network

import com.example.thindie.wantmoex.data.mappers.ShareMapper
import com.example.thindie.wantmoex.data.mappers.getCalculateDate
import com.example.thindie.wantmoex.data.network.retrofit.StockApiService
import com.example.thindie.wantmoex.domain.entities.Share
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val stockApiService: StockApiService,
    private val mapper: ShareMapper
) : RemoteDataSource {


    private suspend fun brandonFlowers(share: Share?): List<Share> {
        return if (share == null) {
            val allShares = stockApiService.getTotalShares()
            mapper.fromRawJsonToList(allShares)
        } else {
            val history = stockApiService.getShare(
                share = share.id,
                from = getCalculateDate()
            )
            mapper.fromRawJsonToList(history)
        }
    }

    override suspend fun loadAll(): Flow<List<Share>> {
        return flow {
            this.emit(brandonFlowers(null))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun loadSingle(share: Share): Flow<List<Share>> {
        return flow {
            this.emit(brandonFlowers(share))
        }.flowOn(Dispatchers.IO)
    }
}