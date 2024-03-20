package kr.bgmsound.bgmlab.authentication.dto

data class AuthenticationDto(
    val type: LoginType,
    val principal: String,
    val credentials: String
)