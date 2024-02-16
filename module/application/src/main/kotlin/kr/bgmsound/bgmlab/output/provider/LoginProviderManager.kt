package kr.bgmsound.bgmlab.output.provider

import kr.bgmsound.bgmlab.LoginProviderType

interface LoginProviderManager {
    fun getSocialLoginProvider(provider: LoginProviderType): SocialLoginProvider
}