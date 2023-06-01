package datasource

import com.bmsk.model.Role
import model.DataResponse
import model.request.SignInReq
import model.request.SignUpReq
import model.response.RefreshTokenRes
import model.response.SignInRes
import model.response.SignUpRes

interface UserRemoteDataSource {
    suspend fun postSignUp(body: SignUpReq): DataResponse<SignUpRes>
    suspend fun postSignIn(body: SignInReq): DataResponse<SignInRes>
    suspend fun getRefreshToken(
        userIdx: Long,
        role: Role,
    ): DataResponse<RefreshTokenRes>
}