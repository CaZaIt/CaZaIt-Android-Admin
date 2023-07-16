package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName

data class SignUpRes(
    @SerializedName("code")
    val code: Int,
    @SerializedName("result")
    val result: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val signUpResult: org.cazait.network.dto.response.SignUpResultDto? = null
)
