package model.dto

import com.google.gson.annotations.SerializedName

data class SignUpResultDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("nickname")
    val nickname: String,
)
