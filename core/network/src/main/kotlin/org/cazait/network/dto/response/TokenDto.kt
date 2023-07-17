package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName
import org.bmsk.domain.model.Role

data class TokenDto(
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
