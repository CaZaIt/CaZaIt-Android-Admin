package model.dto

import com.bmsk.model.Role
import com.google.gson.annotations.SerializedName

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