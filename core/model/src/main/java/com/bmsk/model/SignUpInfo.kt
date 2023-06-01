package com.bmsk.model

data class SignUpInfo(
    val id: Long,
    val email: String,
    val nickname: String,
) {
    companion object {
        fun getEmptyInfo() = SignUpInfo(
            id = 0L,
            email = " ",
            nickname = " "
        )
    }
}

data class EmailDup(
    val isDup: Boolean = false,
    val message: String,
)

data class NicknameDup(
    val isDup: Boolean = false,
    val message: String,
)