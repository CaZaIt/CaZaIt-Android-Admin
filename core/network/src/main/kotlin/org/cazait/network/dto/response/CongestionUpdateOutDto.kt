package org.cazait.network.dto.response

import com.google.gson.annotations.SerializedName
import org.bmsk.domain.model.CongestionStatus
import java.util.UUID

data class CongestionUpdateOutDto(
    @SerializedName("congestionId")
    val congestionId: Long,
    @SerializedName("cafeId")
    val cafeId: UUID,
    @SerializedName("congestionStatus")
    val congestionStatus: CongestionStatus
)