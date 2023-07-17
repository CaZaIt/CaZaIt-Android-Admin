package org.cazait.network.api

import org.bmsk.domain.model.Role
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.SignInResultDto
import org.cazait.network.dto.response.TokenDto
import org.cazait.network.dto.response.UserAuthenticateOutDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthService {
    @POST("/api/auths/log-in")
    suspend fun postSignIn(
        @Body
        signInRequestBody: SignInRequestBody,
        @Query("role")
        role: String = Role.MASTER.value,
    ): Response<CazaitResponse<SignInResultDto>>

    @GET("/api/auths/refresh/{userIdx}")
    suspend fun getRefreshToken(
        @Path("userIdx")
        userIdx: String,
        @Query("role")
        role: String = Role.MASTER.value,
    ): Response<CazaitResponse<TokenDto>>

    @GET("/api/auths/refresh")
    suspend fun getUpdatedAccessToken(
        @Query("role")
        role: String = Role.MASTER.value,
        @Header("Refresh-Token")
        refreshToken: String
    ): Response<CazaitResponse<UserAuthenticateOutDto>>
}