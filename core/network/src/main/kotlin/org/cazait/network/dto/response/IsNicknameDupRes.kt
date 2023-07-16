package org.cazait.network.dto.response

data class IsNicknameDupRes(
    val code: Int,
    val result: String,
    val message: String,
    val data: String,
)
