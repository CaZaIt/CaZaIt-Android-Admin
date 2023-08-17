package org.cazait.network.datasource

import android.util.Log
import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.Role
import org.cazait.network.api.AuthService
import org.cazait.network.api.UserService
import org.cazait.network.dto.request.CheckNicknameReq
import org.cazait.network.dto.request.CheckPhoneNumReq
import org.cazait.network.dto.request.CheckUserIdReq
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.CheckRes
import org.cazait.network.dto.response.SignUpResultDto
import org.cazait.network.dto.response.TokenDto
import org.cazait.network.dto.response.UserAuthenticateOutDto
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    private val userService: UserService,
    private val authService: AuthService,
) : UserRemoteDataSource {

    override fun postSignUp(
        signUpRequestBody: SignUpRequestBody
    ): Flow<Result<SignUpResultDto>> {
        return processCall { userService.postSignUp(signUpRequestBody) }
    }

    override fun postSignIn(signInRequestBody: SignInRequestBody) =
        processCall { authService.postSignIn(signInRequestBody) }

    override fun getRefreshToken(
        userIdx: String, role: Role
    ): Flow<Result<TokenDto>> {
        return processCall { authService.getRefreshToken(userIdx, role.value) }
    }

    override suspend fun postCheckPhoneNum(body: CheckPhoneNumReq): Flow<Result<CheckRes>> {
        return normalProcessCall { userService.postPhoneDB(body) }
    }

    override suspend fun postCheckUserId(body: CheckUserIdReq): Flow<Result<CheckRes>> {
        Log.d("UserRemoteData IdDup", body.toString())
        return normalProcessCall {
            Log.d("UserRemoteData IdDup", userService.postUserIdDB(body).toString())
            userService.postUserIdDB(body)
        }
    }

    override suspend fun postCheckNickname(body: CheckNicknameReq): Flow<Result<CheckRes>> {
        return normalProcessCall { userService.postNicknameDB(body) }
    }

    override fun getUpdatedAccessToken(refreshToken: String): Flow<Result<UserAuthenticateOutDto>> {
        TODO("Not yet implemented")
    }
}