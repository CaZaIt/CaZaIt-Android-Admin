package org.cazait.network.api

import org.cazait.network.dto.request.CafeImageCreateInRequestBody
import org.cazait.network.dto.response.CazaitResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface CafeImageService {
    @POST("/api/cafes/images/object-url")
    suspend fun postCafeImageUrl(
        @Body
        cafeImageCreateInRequestBody: CafeImageCreateInRequestBody
    ): Response<CazaitResponse<String>>

    @DELETE("/api/cafes/images/delete/{cafeImageId}/master/{masterId}")
    suspend fun deleteCafeImage(
        @Path("cafeImageId")
        cafeImageId: Long,
        @Path("masterId")
        masterId: UUID
    ): Response<CazaitResponse<String>>
}
