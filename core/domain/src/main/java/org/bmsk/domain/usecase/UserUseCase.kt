package org.bmsk.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.SignInResult
import org.bmsk.domain.model.SignUpInfo
import org.bmsk.domain.repository.UserRepository
import org.cazait.model.local.UserPreference
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun getCurrentUser(): Flow<UserPreference> {
        return repository.getCurrentUser()
    }

    suspend fun signIn(email: String, password: String): Flow<SignInResult> {
        return repository.signIn(email, password)
    }

    suspend fun signUp(email: String, password: String, nickname: String): Flow<SignUpInfo> {
        return repository.signUp(email, password, nickname)
    }
}