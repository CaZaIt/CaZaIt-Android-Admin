package org.cazait.network.api

import org.cazait.network.dto.request.CafeCreateInRequestBody
import org.cazait.network.dto.response.CafeCreateOutDto
import org.cazait.network.dto.response.CafeUpdateOutDto
import org.cazait.network.dto.response.CazaitResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface CafeService {
    @POST("/api/cafes/update/{cafeId}/master/{masterId}")
    suspend fun postCafeInformation(
        @Path("cafeId")
        cafeId: Long,
        @Path("masterId")
        masterId: UUID,
        @Body
        cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Response<CazaitResponse<CafeUpdateOutDto>>

    @POST("/api/cafes/delete/{cafeId}/master/{masterId}")
    suspend fun postCafeActivation(
        @Path("cafeId")
        cafeId: Long,
        @Path("masterId")
        masterId: UUID
    ): Response<CazaitResponse<String>>

    @POST("/api/cafes/add/master/{masterId}")
    suspend fun postResistCafe(
        @Path("masterId")
        masterId: UUID,
        @Body
        cafeCreateInRequestBody: CafeCreateInRequestBody
    ): Response<CazaitResponse<CafeCreateOutDto>>
}