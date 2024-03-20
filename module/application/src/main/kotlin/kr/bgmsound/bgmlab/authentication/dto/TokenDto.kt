package kr.bgmsound.bgmlab.authentication.dto

import kr.bgmsound.bgmlab.model.Token

data class TokenDto(
    val accessToken: Token,
    val refreshToken: Token
) {
    companion object {
        fun of(accessToken: Token, refreshToken: Token): TokenDto {
            return TokenDto(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }
}