package org.cazait.network.model.request

import com.google.gson.annotations.SerializedName

data class SignInReq(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)
