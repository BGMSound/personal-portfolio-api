package kr.bgmsound.bgmlab.authentication

data class OAuthResult(
    val provider: AuthenticationType,
    val socialId: String
)