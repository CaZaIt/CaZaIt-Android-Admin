package org.cazait.network.dto.request

data class CafeImageCreateInRequestBody(
    val cafeId: Long,
    val imageUrl: List<String>
)