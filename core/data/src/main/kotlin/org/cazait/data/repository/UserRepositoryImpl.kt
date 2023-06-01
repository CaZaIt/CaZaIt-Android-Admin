package org.cazait.data.repository

import com.bmsk.model.Resource
import com.bmsk.model.SignInInfo
import com.bmsk.model.SignUpInfo
import org.cazait.network.datasource.UserRemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import model.DataResponse
import model.request.SignInReq
import model.request.SignUpReq
import org.cazait.data.model.toSignInInfo
import org.cazait.data.model.toSignUpInfo
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl @Inject constructor(
    private val remoteData: UserRemoteData,
    private val ioDispatcher: CoroutineContext,
) : UserRepository {
    override val userId: Long? = null

    override suspend fun signUp(email: String, password: String, nickname: String): Flow<Resource<SignUpInfo>> {
        return flow {
            val body = SignUpReq(email, password, nickname)

            when (val response = remoteData.postSignUp(body)) {
                is DataResponse.Success -> {
                    response.data?.let {
                        emit(Resource.Success(it.signUpResult?.toSignUpInfo()?: SignUpInfo.getEmptyInfo()))
                    } ?: emit(Resource.Error("잘못된 결과입니다."))
                }

                is DataResponse.DataError -> {
                    emit(Resource.Error(response.toString()))
                }
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun refreshToken() {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(email: String, password: String): Flow<Resource<SignInInfo>> {
        return flow {
            val body = SignInReq(email, password)

            when (val response = remoteData.postSignIn(body)) {
                is DataResponse.Success -> {
                    response.data?.signInResult?.toSignInInfo()?.let {
                        emit(Resource.Success(it))
                    }?: emit(Resource.Error(response.toString()))
                }

                is DataResponse.DataError -> {
                    emit(Resource.Error(response.toString()))
                }
            }
        }.flowOn(ioDispatcher)
    }
}
