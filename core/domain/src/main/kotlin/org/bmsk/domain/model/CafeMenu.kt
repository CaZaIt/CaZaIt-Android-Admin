package org.bmsk.domain.model

data class CafeMenu(
    val menuId: Long,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String
)
