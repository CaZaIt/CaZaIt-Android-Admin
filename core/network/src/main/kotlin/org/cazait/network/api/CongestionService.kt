package org.cazait.network.api

import org.cazait.network.dto.request.CongestionRequestBody
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.CongestionUpdateOutDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface CongestionService {
    @POST("/api/congestions/master/{masterId}/cafe/{cafeId}")
    suspend fun postCongestionStatus(
        @Path("masterId")
        masterId: UUID,
        @Path("cafeId")
        cafeId: UUID,
        @Body
        congestionRequestBody: CongestionRequestBody
    ): Response<CazaitResponse<CongestionUpdateOutDto>>
}