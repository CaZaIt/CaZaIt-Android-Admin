package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.bmsk.domain.DomainResult
import org.bmsk.domain.exception.DomainError
import org.bmsk.domain.model.Role
import org.bmsk.domain.model.SignInInfo
import org.bmsk.domain.model.SignUpInfo
import org.bmsk.domain.repository.UserRepository
import org.cazait.datastore.data.repository.UserPreferenceRepository
import org.cazait.model.local.UserPreference
import org.cazait.network.datasource.UserRemoteData
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.CazaitResponse
import org.cazait.network.dto.response.SignInResultDto
import org.cazait.network.dto.response.SignUpResultDto
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl @Inject constructor(
    private val userRemoteData: UserRemoteData,
    private val userPreferenceRepository: UserPreferenceRepository,
    private val ioDispatcher: CoroutineContext,
) : UserRepository {

    override suspend fun signUp(
        loginId: String,
        password: String,
        nickname: String
    ): Flow<DomainResult<SignUpInfo>> {
        return safeApiCallWithData(
            call = { userRemoteData.postSignUp(SignUpRequestBody(loginId, password, nickname)) },
            asDomain = { dto -> dto.asDomain() }
        )
    }

    override suspend fun isNicknameDup(nickname: String): Flow<DomainResult<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun isEmailDup(email: String): Flow<DomainResult<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveSignInInfo(signInInfo: SignInInfo) {
        userPreferenceRepository.updateUserPreference(
            true,
            id = signInInfo.id.toString(),
            loginId = signInInfo.loginId,
            role = Role.MASTER.value,
            accessToken = signInInfo.accessToken,
            refreshToken = signInInfo.refreshToken,
        )
    }

    override suspend fun refreshToken(): Flow<DomainResult<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): Flow<UserPreference> {
        return userPreferenceRepository.getUserPreference()
    }

    override suspend fun signIn(loginId: String, password: String): Flow<DomainResult<SignInInfo>> {
        return safeApiCallWithData(
            call = { userRemoteData.postSignIn(SignInRequestBody(loginId, password)) },
            asDomain = { dto -> dto.asDomain() }
        )
    }

    private suspend fun <T, R> safeApiCallWithData(
        call: suspend () -> Flow<Result<CazaitResponse<T>>>,
        asDomain: (T) -> R
    ): Flow<DomainResult<R>> {
        return flow<DomainResult<R>> {
            call().first().onFailure { exception ->
                when (exception) {
                    is IOException -> emit(DomainResult.Error(DomainError.NetworkError(null)))
                    else -> emit(DomainResult.Error(DomainError.UnKnownError(null)))
                }
            }.onSuccess {
                val data = it.data
                if (data == null) {
                    emit(DomainResult.Error(DomainError.InvalidInputError(it.message)))
                } else {
                    emit(DomainResult.Success(asDomain(data)))
                }
            }

        }.flowOn(ioDispatcher)
    }

    private fun SignUpResultDto.asDomain() = SignUpInfo(id, loginId, nickname)
    private fun SignInResultDto.asDomain() = SignInInfo(
        id = id,
        loginId = loginId,
        accessToken = accessToken,
        refreshToken = refreshToken,
        role = role,
    )
}
