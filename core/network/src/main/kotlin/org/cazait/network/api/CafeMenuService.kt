package org.cazait.network.api

import org.cazait.network.dto.request.PatchCafeMenuRequestBody
import org.cazait.network.dto.request.PostCafeMenuRequestBody
import org.cazait.network.dto.response.CafeMenuDto
import org.cazait.network.dto.response.CazaitResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CafeMenuService {
    @POST("/api/menus/cafe/{cafeId}")
    suspend fun postCafeMenu(
        @Path("cafeId")
        cafeId: Long,
        @Body
        postCafeMenuRequestBody: PostCafeMenuRequestBody
    ): Response<CazaitResponse<CafeMenuDto>>

    @DELETE("/api/menus/{menuId}")
    suspend fun deleteCafeMenu(
        @Path("menuId")
        menuId: Long,
    ): Response<CazaitResponse<String>>

    @PATCH("/api/menus/{menuId}")
    suspend fun patchCafeMenu(
        @Path("menuId")
        menuId: Long,
        @Body
        patchCafeMenuRequestBody: PatchCafeMenuRequestBody
    ): Response<CazaitResponse<CafeMenuDto>>
}