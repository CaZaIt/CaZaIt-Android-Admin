package org.cazait.network.dto.request

data class PostCafeMenuRequestBody(
    val cafeId: Long,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String
)

data class PatchCafeMenuRequestBody(
    val menuId: Long,
    val name: String?,
    val description: String?,
    val price: Int,
    val imageUrl: String
)