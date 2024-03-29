package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.cazait.network.api.CongestionService
import org.cazait.network.dto.request.CongestionRequestBody
import org.cazait.network.dto.response.CongestionUpdateOutDto
import java.util.UUID
import javax.inject.Inject

class CafeCongestionRemoteData @Inject constructor(
    private val congestionService: CongestionService
) : CafeCongestionRemoteDataSource {
    override fun postCafeCongestion(
        masterId: UUID,
        cafeId: UUID,
        requestBody: CongestionRequestBody
    ): Flow<Result<CongestionUpdateOutDto>> {
        return processCall { congestionService.postCongestionStatus(masterId, cafeId, requestBody) }
    }
}