package org.bmsk.domain.model

import java.util.UUID

data class SignUpInfo(
    val id: UUID,
    val loginId: String,
    val nickname: String,
)