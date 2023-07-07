package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.bmsk.domain.ErrorType
import org.bmsk.domain.Result
import org.bmsk.domain.model.SignInInfo
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
    ): Flow<Result<SignUpInfo>> {
        return processRequest(
            request = { remoteData.postSignUp(email, password, nickname) },
            onSuccess = { data ->
                data.signUpResult?.let { Result.Success(it.toSignUpInfo()) }
                    ?: Result.Fail(data.message)
            }
        )
    }

    override suspend fun isNicknameDup(nickname: String): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun isEmailDup(email: String): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): Flow<UserPreference> {
        return userPreferenceRepository.getUserPreference()
    }

    override suspend fun signIn(email: String, password: String): Flow<Result<SignInInfo>> {
        return processRequest(
            request = { remoteData.postSignIn(email, password) },
            onSuccess = { data ->
                data.signInResult?.let { resultData ->
                    userPreferenceRepository.updateUserPreference(
                        isLoggedIn = true,
                        id = resultData.id,
                        email = resultData.email,
                        role = resultData.role,
                        accessToken = resultData.accessToken,
                        refreshToken = resultData.refreshToken,
                    )
                    Result.Success(resultData.asDomain())
                } ?: Result.Fail(data.message)
            }
        )
    }

    private suspend fun <T, R> processRequest(
        request: suspend () -> DataResponse<T>,
        onSuccess: suspend (T) -> Result<R>
    ): Flow<Result<R>> {
        return flow {
            when (val response = request()) {
                is DataResponse.Success -> {
                    response.data?.let { data ->
                        emit(onSuccess(data))
                    } ?: emit(Result.Error(ErrorType.NO_DATA_ERROR))
                }

                is DataResponse.DataError -> {
                    emit(Result.Error(ErrorType.NETWORK_CONNECTION_ERROR))
                }
            }
        }.flowOn(ioDispatcher)
    }

    private fun SignInResultDTO.asDomain() = SignInInfo(
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
