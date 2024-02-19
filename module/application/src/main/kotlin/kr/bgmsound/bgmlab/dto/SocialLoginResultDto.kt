package kr.bgmsound.bgmlab.dto

import kr.bgmsound.bgmlab.output.authentication.LoginProviderType

data class SocialLoginResultDto(
    val provider: LoginProviderType,
    val socialId: String
)