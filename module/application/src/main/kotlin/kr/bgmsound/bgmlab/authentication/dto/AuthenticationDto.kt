package kr.bgmsound.bgmlab.authentication.dto

import kr.bgmsound.bgmlab.authentication.LoginType

data class AuthenticationDto(
    val type: LoginType,
    val principal: String,
    val credentials: String
)