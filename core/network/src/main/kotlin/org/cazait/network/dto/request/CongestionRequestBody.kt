package org.cazait.network.dto.request

import com.google.gson.annotations.SerializedName
import org.bmsk.domain.model.CongestionStatus

data class CongestionRequestBody(
    @SerializedName("congestionStatus")
    val congestionStatus: CongestionStatus
)