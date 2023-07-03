package org.bmsk.domain.model

sealed class SignInResult {
    data class SuccessInfo(
        val email: String,
        val id: Long,
        val accessToken: String,
        val refreshToken: String,
        val role: Role
    ) : SignInResult()

    data class FailInfo(val message: String) : SignInResult()
}

