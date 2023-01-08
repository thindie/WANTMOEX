package com.example.thindie.wantmoex.data

import com.example.thindie.wantmoex.data.mappers.ShareMapper
import com.example.thindie.wantmoex.data.mappers.getCalculateDate
import com.example.thindie.wantmoex.data.network.StockApiService
import com.example.thindie.wantmoex.domain.EntityRepository
import com.example.thindie.wantmoex.domain.entities.Share
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StockDataRepositoryImpl @Inject constructor(
    private val stockApiService: StockApiService,
    private val mapper: ShareMapper,
) : EntityRepository {

    private suspend fun brandonFlowers(share: Share?): Flow<List<Share>> {
        return if (share == null) flow {
            val allShares = stockApiService.getTotalShares()
            emit(mapper.fromRawJsonToList(allShares))
        }.flowOn(Dispatchers.IO)

        else flow {
            val history = stockApiService.getShare(
                share = share.id,
                from = getCalculateDate()
            )
            emit(mapper.fromRawJsonToList(history))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAll(): Flow<List<Share>> {
        return brandonFlowers(null)
    }

    override suspend fun getSingle(share: Share): Flow<List<Share>> {
        return brandonFlowers(share)
    }

}