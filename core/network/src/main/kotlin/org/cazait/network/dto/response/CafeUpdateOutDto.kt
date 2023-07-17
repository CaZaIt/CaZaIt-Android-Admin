package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName

data class CafeUpdateOutDto(
    @SerializedName("cafeId")
    val cafeId: Long
)
