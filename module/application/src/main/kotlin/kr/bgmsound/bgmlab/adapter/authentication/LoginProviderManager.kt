package kr.bgmsound.bgmlab.adapter.authentication

import kr.bgmsound.bgmlab.strategy.LoginProviderType
import kr.bgmsound.bgmlab.gateway.output.authentication.SocialLoginProvider

interface LoginProviderManager {

    fun getSocialLoginProvider(type: LoginProviderType): SocialLoginProvider

}