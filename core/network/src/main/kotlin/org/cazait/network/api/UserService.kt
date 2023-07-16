package org.cazait.network.api

import org.cazait.network.dto.request.SignUpReq
import org.cazait.network.dto.response.SignUpRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface UserService {
    @POST("/api/masters/sign-up")
    suspend fun postSignUp(
        @Body
        signUpReq: SignUpReq
    ): Response<SignUpRes>

    @DELETE("/api/masters/masterId}")
    suspend fun deleteAccount() {

    }
}