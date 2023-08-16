package org.cazait.network.dto.request

import com.google.gson.annotations.SerializedName

data class SignInRequestBody(
    @SerializedName("accountName")
    val accountName: String,
    @SerializedName("password")
    val password: String,
)
