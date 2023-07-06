package org.bmsk.domain.model

data class SignUpInfo(
    val id: String,
    val email: String,
    val nickname: String,
) {
    companion object {
        fun getEmptyInfo() = SignUpInfo(
            id = "",
            email = "",
            nickname = ""
        )
    }
}
