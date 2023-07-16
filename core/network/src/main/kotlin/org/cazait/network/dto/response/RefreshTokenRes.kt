package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName

data class RefreshTokenRes(
    @SerializedName("code")
    private val code: Int,
    @SerializedName("result")
    private val result: String,
    @SerializedName("message")
    private val message: String,
    @SerializedName("data")
    private val tokenDTO: org.cazait.network.dto.response.TokenDto
)
