package kr.bgmsound.bgmlab.application.authentication.dto

import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.TokenType

data class AuthenticationDto(
    val type: TokenType,
    val principal: String,
    val credentials: String,
    val roles: List<Role>
)