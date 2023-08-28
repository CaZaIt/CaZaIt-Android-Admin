package org.bmsk.domain.model

data class ManagedCafe(
    val cafeId: Long = 0,
    val name: String = "",
    val address: String = "",
    val cafeImages: List<CafeImage> = emptyList()
)