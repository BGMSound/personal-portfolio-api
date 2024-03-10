package kr.bgmsound.bgmlab.authentication.provider

import kr.bgmsound.bgmlab.strategy.LoginProviderType
import kr.bgmsound.bgmlab.gateway.input.authentication.SocialLoginProvider
import kr.bgmsound.bgmlab.adapter.authentication.LoginProviderManager
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component

@Component
class LoginProviderManagerImpl(
    private val socialLoginProviders: ObjectProvider<SocialLoginProvider>
) : LoginProviderManager {

    override fun getSocialLoginProvider(type: LoginProviderType): SocialLoginProvider {
        return socialLoginProviders
            .find { it.getType() == type }
            ?: throw IllegalArgumentException("Invalid login provider")
    }
}