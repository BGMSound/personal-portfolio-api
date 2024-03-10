package kr.bgmsound.bgmlab.dto

import kr.bgmsound.bgmlab.strategy.LoginProviderType

data class SocialLoginResultDto(
    val provider: LoginProviderType,
    val socialId: String
)