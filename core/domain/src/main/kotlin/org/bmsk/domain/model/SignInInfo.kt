package org.bmsk.domain.model

import java.util.UUID

data class SignInInfo(
    val id: UUID,
    val loginId: String,
    val accessToken: String,
    val refreshToken: String,
    val role: String
)
