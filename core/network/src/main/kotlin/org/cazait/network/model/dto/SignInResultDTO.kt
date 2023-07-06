package org.cazait.network.model.dto

import com.google.gson.annotations.SerializedName
import org.bmsk.domain.model.Role

data class SignInResultDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("role")
    val role: String
)