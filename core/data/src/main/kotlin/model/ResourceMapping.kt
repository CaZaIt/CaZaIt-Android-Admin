package model

import com.bmsk.model.SignInInfo
import com.bmsk.model.SignUpInfo
import model.dto.SignInResultDTO
import model.dto.SignUpResultDTO

fun SignInResultDTO.toSignInInfo() = SignInInfo(
    email = email,
    id = id,
    jwtToken = jwtToken,
    refreshToken = refreshToken,
    role = role,
)

fun SignUpResultDTO.toSignUpInfo() = SignUpInfo(
    id = id,
    email = email,
    nickname = nickname,
)