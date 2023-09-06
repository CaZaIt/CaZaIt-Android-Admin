package org.bmsk.domain.model

import java.util.UUID

data class ManagedCafe(
    val cafeId: UUID = UUID.randomUUID(),
    val name: String = "",
    val address: String = "",
    val cafeImages: List<CafeImage> = emptyList()
)