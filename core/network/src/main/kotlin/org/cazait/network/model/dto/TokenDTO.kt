package model.dto

import com.bmsk.model.Role
import com.google.gson.annotations.SerializedName

data class TokenDTO(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("email")
    private val email: String,
    @SerializedName("jwtToken")
    private val jwtToken: String,
    @SerializedName("refreshToken")
    private val refreshToken: String,
    @SerializedName("role")
    private val role: Role,
)
