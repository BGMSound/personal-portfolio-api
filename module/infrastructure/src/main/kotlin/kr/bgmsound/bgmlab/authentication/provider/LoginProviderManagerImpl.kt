package kr.bgmsound.bgmlab.authentication.provider

import kr.bgmsound.bgmlab.output.authentication.LoginProviderType
import kr.bgmsound.bgmlab.output.authentication.SocialLoginProvider
import kr.bgmsound.bgmlab.output.authentication.LoginProviderManager
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