package api

import model.request.SignUpReq
import model.response.SignUpRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/masters/sign-up")
    suspend fun postSignUp(
        @Body
        signUpReq: SignUpReq
    ): Response<SignUpRes>
}