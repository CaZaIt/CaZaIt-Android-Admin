package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.cazait.network.dto.request.CongestionRequestBody
import org.cazait.network.dto.response.CongestionUpdateOutDto
import java.util.UUID

interface CafeCongestionRemoteDataSource {
    fun postCafeCongestion(
        masterId: UUID,
        cafeId: UUID,
        requestBody: CongestionRequestBody
    ): Flow<Result<CongestionUpdateOutDto>>
}