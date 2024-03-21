package kr.bgmsound.bgmlab.authentication.dto

import kr.bgmsound.bgmlab.authentication.LoginType

data class OAuthResult(
    val provider: LoginType,
    val socialId: String
)