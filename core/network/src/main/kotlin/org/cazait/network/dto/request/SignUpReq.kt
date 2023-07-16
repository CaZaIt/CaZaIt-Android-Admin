package org.cazait.network.dto.request

import com.google.gson.annotations.SerializedName

data class SignUpReq(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
)
