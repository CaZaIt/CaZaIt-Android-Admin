package org.cazait.network.dto.request

data class CheckPhoneNumReq(
    val phoneNumber: String,
    val isExist: String
)

data class CheckNicknameReq(
    val nickname: String,
    val isExist: String
)

data class CheckUserIdReq(
    val accountName: String,
    val isExist: String
)