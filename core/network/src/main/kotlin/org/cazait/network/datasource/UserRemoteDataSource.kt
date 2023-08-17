package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.Role
import org.cazait.network.dto.request.CheckNicknameReq
import org.cazait.network.dto.request.CheckPhoneNumReq
import org.cazait.network.dto.request.CheckUserIdReq
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.CheckRes
import org.cazait.network.dto.response.SignInResultDto
import org.cazait.network.dto.response.SignUpResultDto
import org.cazait.network.dto.response.TokenDto
import org.cazait.network.dto.response.UserAuthenticateOutDto

interface UserRemoteDataSource {
    fun postSignUp(
        signUpRequestBody: SignUpRequestBody
    ): Flow<Result<SignUpResultDto>>

    fun postSignIn(
        signInRequestBody: SignInRequestBody
    ): Flow<Result<SignInResultDto>>

    fun getRefreshToken(
        userIdx: String,
        role: Role = Role.MASTER,
    ): Flow<Result<TokenDto>>

    suspend fun postCheckPhoneNum(body: CheckPhoneNumReq): Flow<Result<CheckRes>>
    suspend fun postCheckUserId(body: CheckUserIdReq): Flow<Result<CheckRes>>
    suspend fun postCheckNickname(body: CheckNicknameReq): Flow<Result<CheckRes>>

    fun getUpdatedAccessToken(refreshToken: String): Flow<Result<UserAuthenticateOutDto>>
}