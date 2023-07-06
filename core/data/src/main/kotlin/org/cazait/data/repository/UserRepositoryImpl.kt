package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.bmsk.domain.model.SignInResult
import org.bmsk.domain.model.SignUpInfo
import org.bmsk.domain.repository.UserRepository
import org.cazait.datastore.data.repository.UserPreferenceRepository
import org.cazait.model.local.UserPreference
import org.cazait.network.datasource.UserRemoteData
import org.cazait.network.model.DataResponse
import org.cazait.network.model.dto.SignInResultDTO
import org.cazait.network.model.dto.SignUpResultDTO
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl @Inject constructor(
    private val remoteData: UserRemoteData,
    private val userPreferenceRepository: UserPreferenceRepository,
    private val ioDispatcher: CoroutineContext,
) : UserRepository {

    override suspend fun signUp(
        email: String,
        password: String,
        nickname: String
    ): Flow<SignUpInfo> {
        return flow {
            when (val response = remoteData.postSignUp(email, password, nickname)) {
                is DataResponse.Success -> {
                    response.data?.signUpResult?.let {
                        emit(it.toSignUpInfo())
                    }
                }

                is DataResponse.DataError -> {
                    emit(SignUpInfo.getEmptyInfo())
                }
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun isNicknameDup(nickname: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun isEmailDup(email: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): Flow<UserPreference> {
        return userPreferenceRepository.getUserPreference()
    }

    override suspend fun signIn(email: String, password: String): Flow<SignInResult> {
        return flow {
            when (val response = remoteData.postSignIn(email, password)) {
                is DataResponse.Success -> {
                    response.data?.let {
                        it.signInResult?.let { resultData ->
                            userPreferenceRepository.updateUserPreference(
                                isLoggedIn = true,
                                id = resultData.id,
                                email = resultData.email,
                                role = resultData.role,
                                accessToken = resultData.accessToken,
                                refreshToken = resultData.refreshToken,
                            )
                            emit(resultData.asDomain())
                        } ?: emit(SignInResult.FailInfo(it.message))
                    } ?: emit(SignInResult.FailInfo("로그인에 실패했습니다."))
                }

                is DataResponse.DataError -> {
                    emit(SignInResult.FailInfo("로그인에 실패했습니다. 네트워크 연결을 확인해주세요."))
                }
            }
        }.flowOn(ioDispatcher)
    }

    private fun SignInResultDTO.asDomain() = SignInResult.SuccessInfo(
        email = email,
        id = id,
        accessToken = accessToken,
        refreshToken = refreshToken,
        role = role,
    )

    private fun SignUpResultDTO.toSignUpInfo() = SignUpInfo(
        id = id,
        email = email,
        nickname = nickname,
    )
}
