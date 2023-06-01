package model.response

import com.google.gson.annotations.SerializedName
import model.dto.SignInResultDTO

data class SignInRes(
    @SerializedName("code")
    val code: Int,
    @SerializedName("result")
    val result: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val signInResult: SignInResultDTO? = null
)
