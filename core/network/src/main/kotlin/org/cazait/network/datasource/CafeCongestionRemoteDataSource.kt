package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.cazait.network.dto.request.CongestionRequestBody
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.CongestionUpdateOutDto
import java.util.UUID

interface CafeCongestionRemoteDataSource {
    suspend fun postCafeCongestion(
        masterId: UUID,
        cafeId: Long,
        requestBody: CongestionRequestBody
    ): Flow<Result<CongestionUpdateOutDto>>
}