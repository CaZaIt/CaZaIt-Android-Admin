package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName

data class ManagedCafeListOutDto(
    @SerializedName("cafeId")
    val cafeId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("cafeImages")
    val cafeImages: List<ImageInformationDto>
)
