package org.cazait.network.dto

import org.bmsk.domain.exception.ErrorStatus

data class ErrorData(
    val code: Int,
    val result: String,
    val error: ErrorStatus,
    val message: String,
)
