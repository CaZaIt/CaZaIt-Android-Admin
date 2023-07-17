package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class UserAuthenticateOutDto(
    @SerializedName("id")
    val id: UUID,
    @SerializedName("accountNumber")
    val accountName: String,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("role")
    val role: String
)
