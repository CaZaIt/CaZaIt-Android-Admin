package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class ManagedCafeListOutDto(
    @SerializedName("cafeId")
    val cafeId: UUID,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("cafeImages")
    val cafeImages: List<ImageInformationDto>
)
