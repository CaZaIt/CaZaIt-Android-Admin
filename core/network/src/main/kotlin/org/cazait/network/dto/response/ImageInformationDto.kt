package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName

data class ImageInformationDto(
    @SerializedName("imageId")
    val imageId: Long,
    @SerializedName("url")
    val url: String
)
