package org.cazait.network.dto.request

import com.google.gson.annotations.SerializedName

data class SignInRequestBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)
