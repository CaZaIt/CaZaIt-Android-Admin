package org.bmsk.domain.repository

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.Result
import org.bmsk.domain.model.SignInInfo
import org.bmsk.domain.model.SignUpInfo
import org.cazait.model.local.UserPreference

interface UserRepository {
    suspend fun getCurrentUser(): Flow<UserPreference>
    suspend fun signIn(email: String, password: String): Flow<Result<SignInInfo>>
    suspend fun signUp(email: String, password: String, nickname: String): Flow<Result<SignUpInfo>>
    suspend fun refreshToken(): Flow<Result<String>>

    suspend fun isNicknameDup(nickname: String): Flow<Result<Boolean>>
    suspend fun isEmailDup(email: String): Flow<Result<Boolean>>
}