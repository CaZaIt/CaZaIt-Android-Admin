package org.cazait.network.api

import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.SignUpResultDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface UserService {
    @POST("/api/masters/sign-up")
    suspend fun postSignUp(
        @Body
        signUpReq: SignUpRequestBody
    ): Response<CazaitResponse<SignUpResultDto>>

    @DELETE("/api/masters/masterId}")
    suspend fun deleteAccount() {

    }
}