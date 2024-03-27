package kr.bgmsound.bgmlab.authentication.dto

import kr.bgmsound.bgmlab.authentication.AuthenticationType

data class AuthenticationDto(
    val type: AuthenticationType,
    val principal: String,
    val credentials: String
)