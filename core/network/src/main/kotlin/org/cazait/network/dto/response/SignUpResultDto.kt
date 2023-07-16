package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName

data class SignUpResultDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("nickname")
    val nickname: String,
)
