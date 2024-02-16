package kr.bgmsound.bgmlab.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(accessToken: String, refreshToken: String) = TokenDto(accessToken, refreshToken)
    }
}