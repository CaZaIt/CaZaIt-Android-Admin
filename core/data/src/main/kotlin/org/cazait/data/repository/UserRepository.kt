package org.cazait.data.repository

import com.bmsk.model.EmailDup
import com.bmsk.model.NicknameDup
import com.bmsk.model.Resource
import com.bmsk.model.SignInInfo
import com.bmsk.model.SignUpInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val userId: Long?
    suspend fun signUp(email: String, password: String, nickname: String): Flow<Resource<SignUpInfo>>
//    suspend fun isNicknameDup(nickname: String): Flow<Resource<NicknameDup>>
//    suspend fun isEmailDup(email: String): Flow<Resource<EmailDup>>

    suspend fun signIn(email: String, password: String): Flow<Resource<SignInInfo>>
    suspend fun refreshToken()
}
