package org.bmsk.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.SignInInfo
import org.bmsk.domain.model.SignUpInfo
import org.cazait.model.local.UserPreference

interface UserRepository {
    suspend fun getCurrentUser(): Result<UserPreference>
    suspend fun deleteUserInformation(): UserPreference
    fun signIn(accountName: String, password: String): Flow<Result<SignInInfo>>
    fun signUp(loginId: String, password: String, nickname: String): Flow<Result<SignUpInfo>>
    fun refreshToken(): Flow<Result<String>>

    fun isNicknameDup(nickname: String): Flow<Result<Boolean>>
    fun isEmailDup(email: String): Flow<Result<Boolean>>

    suspend fun saveSignInInfo(signInInfo: SignInInfo)
}