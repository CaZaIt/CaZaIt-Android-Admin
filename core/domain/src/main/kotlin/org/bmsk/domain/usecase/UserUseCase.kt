package org.bmsk.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.DomainResult
import org.bmsk.domain.model.SignInInfo
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

    suspend fun signIn(loginId: String, password: String): Flow<DomainResult<SignInInfo>> {
        return repository.signIn(loginId, password)
    }

    suspend fun signUp(loginId: String, password: String, nickname: String): Flow<DomainResult<SignUpInfo>> {
        return repository.signUp(loginId, password, nickname)
    }

    suspend fun saveUserSignInformation(signInInfo: SignInInfo) {
        repository.saveSignInInfo(signInInfo)
    }
}