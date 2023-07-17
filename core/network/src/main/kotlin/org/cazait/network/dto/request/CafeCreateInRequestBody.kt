package org.cazait.network.dto.request

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class CafeCreateInRequestBody(
    @SerializedName("masterId")
    val masterId: UUID,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String
)
