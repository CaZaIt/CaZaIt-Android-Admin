package org.cazait.network.datasource

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.Role
import org.cazait.network.api.AuthService
import org.cazait.network.api.UserService
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.SignUpResultDto
import org.cazait.network.dto.response.TokenDto
import org.cazait.network.dto.response.UserAuthenticateOutDto
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    private val userService: UserService,
    private val authService: AuthService,
) : UserRemoteDataSource {

    override suspend fun postSignUp(
        signUpRequestBody: SignUpRequestBody
    ): Flow<Result<CazaitResponse<SignUpResultDto>>> {
        return processCall { userService.postSignUp(signUpRequestBody) }
    }

    override suspend fun postSignIn(signInRequestBody: SignInRequestBody) =
        processCall { authService.postSignIn(signInRequestBody) }

    override suspend fun getRefreshToken(
        userIdx: String, role: Role
    ): Flow<Result<CazaitResponse<TokenDto>>> {
        return processCall { authService.getRefreshToken(userIdx, role.value) }
    }

    override suspend fun postIsEmailDup(email: String): Flow<Result<CazaitResponse<String>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpdatedAccessToken(refreshToken: String): Flow<Result<CazaitResponse<UserAuthenticateOutDto>>> {
        TODO("Not yet implemented")
    }
}