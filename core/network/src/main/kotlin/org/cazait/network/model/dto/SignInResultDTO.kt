package org.cazait.network.model.dto

import com.google.gson.annotations.SerializedName
import org.bmsk.domain.model.Role

data class SignInResultDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("jwtToken")
    val jwtToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("role")
    val role: Role
)