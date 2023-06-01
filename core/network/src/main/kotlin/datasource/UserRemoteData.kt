package datasource

import NetworkConnectivity
import android.provider.ContactsContract.Data
import api.AuthService
import api.UserService
import com.bmsk.model.LoggedInUser
import com.bmsk.model.Role
import model.DataResponse
import model.NETWORK_ERROR
import model.NO_INTERNET_CONNECTION
import model.dto.TokenDTO
import model.request.SignInReq
import model.request.SignUpReq
import model.response.RefreshTokenRes
import model.response.SignInRes
import model.response.SignUpRes
import retrofit2.Response
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

class UserRemoteData @Inject constructor(
    private val userService: UserService,
    private val authService: AuthService,
    private val networkConnectivity: NetworkConnectivity
) : UserRemoteDataSource {

    override suspend fun postSignUp(body: SignUpReq): DataResponse<SignUpRes> {
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

    override suspend fun postSignIn(body: SignInReq): DataResponse<SignInRes> {
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
        userIdx: Long,
        role: Role
    ): DataResponse<RefreshTokenRes> {
        return when (val response = processCall {
            authService.getRefreshToken(userIdx, role)
        }) {
            is RefreshTokenRes -> {
                DataResponse.Success(data = response)
            }

            else -> {
                DataResponse.DataError(errorCode = response as Int)
            }
        }
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