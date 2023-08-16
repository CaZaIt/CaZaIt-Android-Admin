package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.Role
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.request.SignUpRequestBody
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

    fun postIsEmailDup(email: String): Flow<Result<String>>

    fun getUpdatedAccessToken(refreshToken: String): Flow<Result<UserAuthenticateOutDto>>
}