package kr.bgmsound.bgmlab.output.provider

import kr.bgmsound.bgmlab.LoginProviderType

interface SocialLoginProviderManager {
    fun getSocialLoginProvider(provider: LoginProviderType): SocialLoginProvider
}