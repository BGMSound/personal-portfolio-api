package kr.bgmsound.bgmlab.authentication.dto

data class OAuthResult(
    val provider: LoginType,
    val socialId: String
)