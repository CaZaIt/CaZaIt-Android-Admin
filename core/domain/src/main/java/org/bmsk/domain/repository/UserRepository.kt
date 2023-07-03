package org.bmsk.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.SignInResult
import org.bmsk.domain.model.SignUpInfo
import org.cazait.model.local.UserPreference

interface UserRepository {
    suspend fun getCurrentUser(): Flow<UserPreference>
    suspend fun signIn(email: String, password: String): Flow<SignInResult>
    suspend fun signUp(email: String, password: String, nickname: String): Flow<SignUpInfo>
    suspend fun refreshToken(): Flow<String>

    suspend fun isNicknameDup(nickname: String): Flow<Boolean>
    suspend fun isEmailDup(email: String): Flow<Boolean>
}