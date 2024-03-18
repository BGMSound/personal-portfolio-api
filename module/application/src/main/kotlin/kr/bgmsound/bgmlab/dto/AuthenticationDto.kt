package kr.bgmsound.bgmlab.dto

import kr.bgmsound.bgmlab.strategy.LoginProviderType

data class AuthenticationDto(
    val type: LoginProviderType,
    val principal: String,
    val credentials: String
)