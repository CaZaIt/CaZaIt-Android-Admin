package org.bmsk.domain.model

sealed class SignInResult {
    data class SuccessInfo(
        val email: String,
        val id: String,
        val accessToken: String,
        val refreshToken: String,
        val role: String
    ) : SignInResult()

    data class FailInfo(val message: String) : SignInResult()
}

