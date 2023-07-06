package org.cazait.network.api

import org.bmsk.domain.model.Role
import org.cazait.network.model.request.SignInReq
import org.cazait.network.model.response.RefreshTokenRes
import org.cazait.network.model.response.SignInRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthService {
    @POST("/api/auths/log-in")
    suspend fun postSignIn(
        @Body
        signInReq: SignInReq,
        @Query("role")
        role: String = Role.MASTER.value,
    ): Response<SignInRes>

    @GET("/api/auths/refresh/{userIdx}")
    suspend fun getRefreshToken(
        @Path("userIdx")
        userIdx: String,
        @Query("role")
        role: String = Role.MASTER.value,
    ): Response<RefreshTokenRes>
}