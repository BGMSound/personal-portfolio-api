package kr.bgmsound.bgmlab.output.authentication

interface LoginProviderManager {

    fun getSocialLoginProvider(type: LoginProviderType): SocialLoginProvider

}