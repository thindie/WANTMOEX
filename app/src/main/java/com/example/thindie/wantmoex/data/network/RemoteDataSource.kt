package com.example.thindie.wantmoex.data.network

import com.example.thindie.wantmoex.domain.entities.Share
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
   suspend fun loadAll() : Flow<List<Share>>
   suspend fun loadSingle(share: Share) : Flow<List<Share>>
}