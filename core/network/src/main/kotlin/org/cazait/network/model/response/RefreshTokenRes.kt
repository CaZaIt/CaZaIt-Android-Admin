package model.response

import com.google.gson.annotations.SerializedName
import model.dto.TokenDTO

data class RefreshTokenRes(
    @SerializedName("code")
    private val code: Int,
    @SerializedName("result")
    private val result: String,
    @SerializedName("message")
    private val message: String,
    @SerializedName("data")
    private val tokenDTO: TokenDTO
)
