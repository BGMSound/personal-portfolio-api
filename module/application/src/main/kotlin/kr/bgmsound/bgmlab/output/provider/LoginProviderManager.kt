package kr.bgmsound.bgmlab.output.provider

interface LoginProviderManager {
    fun getSocialLoginProvider(provider: LoginProviderType): SocialLoginProvider
}