package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class SignUpResultDto(
    @SerializedName("id")
    val id: UUID,
    @SerializedName("accountNumber")
    val loginId: String,
    @SerializedName("nickname")
    val nickname: String,
)
