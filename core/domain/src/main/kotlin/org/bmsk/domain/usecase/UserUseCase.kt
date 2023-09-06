package org.bmsk.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.bmsk.domain.model.SignInInfo
import org.bmsk.domain.model.SignUpInfo
import org.bmsk.domain.repository.UserRepository
import org.cazait.model.local.UserPreference
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun getCurrentUser(): UserPreference {
        return repository.getCurrentUser().getOrElse {
            repository.deleteUserInformation()
        }
    }

    fun signIn(accountName: String, password: String) = repository.signIn(accountName, password)

    fun signUp(loginId: String, password: String, nickname: String): Flow<Result<SignUpInfo>> {
        return repository.signUp(loginId, password, nickname)
    }

    suspend fun saveUserSignInformation(signInInfo: SignInInfo) =
        repository.saveSignInInfo(signInInfo)
}