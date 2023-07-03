package org.cazait.network.api

import org.cazait.network.model.request.SignUpReq
import org.cazait.network.model.response.SignUpRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/org/cazait/network/api/masters/sign-up")
    suspend fun postSignUp(
        @Body
        signUpReq: SignUpReq
    ): Response<SignUpRes>
}