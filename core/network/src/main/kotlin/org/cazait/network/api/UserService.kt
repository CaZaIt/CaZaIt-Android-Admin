package org.cazait.network.api

import org.cazait.network.dto.request.CheckNicknameReq
import org.cazait.network.dto.request.CheckPhoneNumReq
import org.cazait.network.dto.request.CheckUserIdReq
import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.CheckRes
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

    @POST("/api/users/exist/phonenumber")
    suspend fun postPhoneDB(@Body checkPhoneNum: CheckPhoneNumReq): Response<CheckRes>

    @POST("/api/users/exist/nickname")
    suspend fun postNicknameDB(@Body checkNickname: CheckNicknameReq): Response<CheckRes>

    @POST("/api/users/exist/accountname")
    suspend fun postUserIdDB(@Body checkUserId: CheckUserIdReq): Response<CheckRes>

    @DELETE("/api/masters/masterId}")
    suspend fun deleteAccount() {

    }
}