package org.cazait.presentation.model

data class StoreState(
    val congestion: Congestion
)

enum class Congestion {
    FREE, NORMAL, CROWED, VERY_CROWED
}
