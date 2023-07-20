package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.DomainResult
import org.bmsk.domain.model.Role
import org.bmsk.domain.model.SignInInfo
import org.bmsk.domain.model.SignUpInfo
import org.bmsk.domain.repository.UserRepository
import org.cazait.data.caller.ApiCaller
import org.cazait.datastore.data.repository.UserPreferenceRepository
import org.cazait.model.local.UserPreference
import org.cazait.network.datasource.UserRemoteData
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.SignInResultDto
import org.cazait.network.dto.response.SignUpResultDto
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteData: UserRemoteData,
    private val userPreferenceRepository: UserPreferenceRepository,
    private val apiCaller: ApiCaller
) : UserRepository {

    override suspend fun signUp(
        loginId: String,
        password: String,
        nickname: String
    ): Flow<DomainResult<SignUpInfo>> {
        return apiCaller.safeApiCallWithData(
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
        return apiCaller.safeApiCallWithData(
            call = { userRemoteData.postSignIn(SignInRequestBody(loginId, password)) },
            asDomain = { dto -> dto.asDomain() }
        )
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
