package org.cazait.model.local

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserPreference(
    val isLoggedIn: Boolean,
    val id: String,
    val loginId: String,
    val role: String,
    val accessToken: String,
    val refreshToken: String,
) {
    val uuid: UUID get() = UUID.fromString(id)

    companion object {
        fun getDefaultInstance() = UserPreference(
            isLoggedIn = false,
            id = "",
            loginId = "",
            role = "",
            accessToken = "",
            refreshToken = "",
        )
    }
}
