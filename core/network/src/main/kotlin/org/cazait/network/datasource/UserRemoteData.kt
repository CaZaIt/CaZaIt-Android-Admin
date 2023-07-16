package org.cazait.network.datasource

import org.bmsk.domain.model.Role
import org.cazait.network.NetworkConnectivity
import org.cazait.network.api.AuthService
import org.cazait.network.api.UserService
import org.cazait.network.model.DataResponse
import org.cazait.network.model.NETWORK_ERROR
import org.cazait.network.model.NO_INTERNET_CONNECTION
import org.cazait.network.dto.request.IsNicknameDupReq
import org.cazait.network.dto.request.SignInReq
import org.cazait.network.dto.request.SignUpReq
import org.cazait.network.dto.response.RefreshTokenRes
import org.cazait.network.dto.response.SignInRes
import org.cazait.network.dto.response.SignUpRes
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    private val userService: UserService,
    private val authService: AuthService,
    private val networkConnectivity: NetworkConnectivity
) : UserRemoteDataSource {

    override suspend fun postSignUp(
        email: String,
        password: String,
        nickname: String
    ): DataResponse<SignUpRes> {
        val body = SignUpReq(email, password, nickname)

        return when (val response = processCall {
            userService.postSignUp(body)
        }) {
            is SignUpRes -> {
                DataResponse.Success(data = response)
            }

            else -> {
                DataResponse.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun postSignIn(email: String, password: String): DataResponse<SignInRes> {
        val body = SignInReq(email, password)
        return when (val response = processCall {
            authService.postSignIn(body)
        }) {
            is SignInRes -> {
                DataResponse.Success(data = response)
            }

            else -> {
                DataResponse.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun getRefreshToken(
        userIdx: String,
        role: Role
    ): DataResponse<RefreshTokenRes> {
        return when (val response = processCall {
            authService.getRefreshToken(userIdx, role.value)
        }) {
            is RefreshTokenRes -> {
                DataResponse.Success(data = response)
            }

            else -> {
                DataResponse.DataError(errorCode = response as Int)
            }
        }
    }

    override suspend fun postIsEmailDup(email: String): DataResponse<IsNicknameDupReq> {
        TODO("Not yet implemented")
    }

    private suspend fun processCall(
        responseCall: suspend () -> Response<*>
    ): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }

        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}