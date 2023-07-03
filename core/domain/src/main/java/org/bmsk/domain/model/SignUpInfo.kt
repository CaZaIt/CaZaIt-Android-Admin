package org.bmsk.domain.model

data class SignUpInfo(
    val id: Long,
    val email: String,
    val nickname: String,
) {
    companion object {
        fun getEmptyInfo() = SignUpInfo(
            id = -99L,
            email = "",
            nickname = ""
        )
    }
}
