package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.Role
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.SignInResultDto
import org.cazait.network.dto.response.SignUpResultDto
import org.cazait.network.dto.response.TokenDto
import org.cazait.network.dto.response.UserAuthenticateOutDto

interface UserRemoteDataSource {
    suspend fun postSignUp(
        signUpRequestBody: SignUpRequestBody
    ): Flow<Result<CazaitResponse<SignUpResultDto>>>

    suspend fun postSignIn(
        signInRequestBody: SignInRequestBody
    ): Flow<Result<CazaitResponse<SignInResultDto>>>

    suspend fun getRefreshToken(
        userIdx: String,
        role: Role = Role.MASTER,
    ): Flow<Result<CazaitResponse<TokenDto>>>

    suspend fun postIsEmailDup(email: String): Flow<Result<CazaitResponse<String>>>

    suspend fun getUpdatedAccessToken(refreshToken: String): Flow<Result<CazaitResponse<UserAuthenticateOutDto>>>
}