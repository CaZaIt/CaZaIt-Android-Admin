package org.cazait.network.dto.request

import com.google.gson.annotations.SerializedName

data class SignUpRequestBody(
    @SerializedName("accountName")
    val loginId: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("nickname")
    val nickname: String,
)
