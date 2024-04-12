package kr.bgmsound.bgmlab.application.authentication.dto

import kr.bgmsound.bgmlab.application.authentication.AuthenticationType

data class AuthenticationRequestDto(
    val type: AuthenticationType,
    val principal: String,
    val credentials: String
)