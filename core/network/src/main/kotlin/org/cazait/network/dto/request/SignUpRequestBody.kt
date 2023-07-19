package org.cazait.network.dto.request

import com.google.gson.annotations.SerializedName

data class SignUpRequestBody(
    @SerializedName("accountNumber")
    val loginId: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
)
