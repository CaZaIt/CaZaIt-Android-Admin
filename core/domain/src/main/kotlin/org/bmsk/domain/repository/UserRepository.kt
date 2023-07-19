package org.bmsk.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.DomainResult
import org.bmsk.domain.model.SignInInfo
import org.bmsk.domain.model.SignUpInfo
import org.cazait.model.local.UserPreference

interface UserRepository {
    suspend fun getCurrentUser(): Flow<UserPreference>
    suspend fun signIn(loginId: String, password: String): Flow<DomainResult<SignInInfo>>
    suspend fun signUp(loginId: String, password: String, nickname: String): Flow<DomainResult<SignUpInfo>>
    suspend fun refreshToken(): Flow<DomainResult<String>>

    suspend fun isNicknameDup(nickname: String): Flow<DomainResult<Boolean>>
    suspend fun isEmailDup(email: String): Flow<DomainResult<Boolean>>

    suspend fun saveSignInInfo(signInInfo: SignInInfo)
}