package kr.bgmsound.bgmlab.authentication

data class OAuthResult(
    val provider: LoginType,
    val socialId: String
)