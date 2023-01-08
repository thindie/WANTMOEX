package com.example.thindie.wantmoex.domain

import com.example.thindie.wantmoex.domain.entities.Share
import kotlinx.coroutines.flow.Flow

interface EntityRepository {
    suspend fun  getAll(): Flow<List<Share>>
    suspend fun  getSingle(share: Share): Flow<List<Share>>
}