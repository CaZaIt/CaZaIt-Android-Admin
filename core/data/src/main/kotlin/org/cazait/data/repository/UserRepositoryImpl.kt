package org.cazait.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.bmsk.domain.model.SignInInfo
import org.bmsk.domain.model.SignUpInfo
import org.bmsk.domain.repository.UserRepository
import org.cazait.datastore.data.repository.UserPreferenceRepository
import org.cazait.model.local.UserPreference
import org.cazait.network.datasource.UserRemoteData
import org.cazait.network.dto.request.CheckNicknameReq
import org.cazait.network.dto.request.CheckPhoneNumReq
import org.cazait.network.dto.request.CheckUserIdReq
import org.cazait.network.dto.request.SignInRequestBody
import org.cazait.network.dto.request.SignUpRequestBody
import org.cazait.network.dto.response.SignInResultDto
import org.cazait.network.dto.response.SignUpResultDto
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteData: UserRemoteData,
    private val userPreferenceRepository: UserPreferenceRepository,
) : UserRepository {
    override suspend fun getCurrentUser() = userPreferenceRepository.getUserPreference()
    override suspend fun deleteUserInformation(): UserPreference {
        return userPreferenceRepository.clearUserPreference()
    }

    override fun signIn(accountName: String, password: String): Flow<Result<SignInInfo>> {
        return userRemoteData.postSignIn(SignInRequestBody(accountName, password)).map {
            it.mapCatching { data ->
                data.asDomain()
            }
        }
    }

    override fun signUp(loginId: String, password: String, phoneNumber: String, nickname: String) =
        flow<Result<SignUpInfo>> {
            userRemoteData.postSignUp(
                SignUpRequestBody(loginId, password, phoneNumber, nickname)
            ).map {
                it.onSuccess { dto ->
                    emit(Result.success(dto.asDomain()))
                }.onFailure { t ->
                    emit(Result.failure(t))
                }
            }
        }

    override fun refreshToken(): Flow<Result<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun checkPhoneNumDB(
        phoneNumber: String,
        isExist: String
    ): Flow<Result<String>> {
        return flow {
            userRemoteData.postCheckPhoneNum(CheckPhoneNumReq(phoneNumber, isExist)).map {
                it.onSuccess { res ->
                    emit(Result.success(res.message))
                }.onFailure { t ->
                    emit(Result.failure(t))
                }
            }
        }
    }

    override suspend fun checkUserIdDB(userId: String, isExist: String): Flow<Result<String>> {
        return flow {
            userRemoteData.postCheckUserId(CheckUserIdReq(userId, isExist)).map {
                it.onSuccess { res ->
                    emit(Result.success(res.message))
                }.onFailure { t ->
                    emit(Result.failure(t))
                }
            }
        }
    }

    override suspend fun checkNicknameDB(nickname: String, isExist: String): Flow<Result<String>> {
        return flow {
            userRemoteData.postCheckNickname(CheckNicknameReq(nickname, isExist)).map {
                it.onSuccess { res ->
                    emit(Result.success(res.message))
                }.onFailure { t ->
                    emit(Result.failure(t))
                }
            }
        }
    }

    override suspend fun saveSignInInfo(signInInfo: SignInInfo) {
        userPreferenceRepository.updateUserPreference(
            isLoggedIn = true,
            id = signInInfo.id.toString(),
            accountName = signInInfo.accountName,
            role = signInInfo.role,
            accessToken = signInInfo.accessToken,
            refreshToken = signInInfo.refreshToken
        )
    }

}

private fun SignInResultDto.asDomain() = SignInInfo(
    id, accountName, accessToken, refreshToken, role
)

private fun SignUpResultDto.asDomain() = SignUpInfo(
    id, loginId, nickname
)