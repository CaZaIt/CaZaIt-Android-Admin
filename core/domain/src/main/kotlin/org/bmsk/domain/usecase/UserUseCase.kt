package org.bmsk.domain.usecase

import android.util.Log
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

    fun signUp(
        loginId: String,
        password: String,
        phoneNumber: String,
        nickname: String
    ): Flow<Result<SignUpInfo>> {
        return repository.signUp(loginId, password, phoneNumber, nickname)
    }

    fun checkPhoneDup(phoneNumber: String, isExist: String): Flow<Result<String>> {
        return repository.checkPhoneNumDB(phoneNumber, isExist)
    }

    fun checkIdDup(loginId: String, isExist: String): Flow<Result<String>> {
        Log.d("UserUserCase", "usecase")
        return repository.checkUserIdDB(loginId, isExist)
    }

    fun checkNicknameDup(nickname: String, isExist: String): Flow<Result<String>> {
        return repository.checkNicknameDB(nickname, isExist)
    }

    fun receiveCode(phoneNumber: String): Flow<Result<String>>? {
        return null
    }

    fun sendCode(phoneNumber: String, code: Int): Flow<Result<String>>? {
        return null
    }

    suspend fun saveUserSignInformation(signInInfo: SignInInfo) {
        repository.saveSignInInfo(signInInfo)
    }
}