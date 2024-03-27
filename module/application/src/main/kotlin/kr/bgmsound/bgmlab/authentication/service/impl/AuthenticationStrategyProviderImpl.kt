package kr.bgmsound.bgmlab.authentication.service.impl

import kr.bgmsound.bgmlab.authentication.AuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.AuthenticationStrategyProvider
import kr.bgmsound.bgmlab.authentication.NativeAuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.OAuthAuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.AuthenticationType
import org.springframework.stereotype.Component

@Component
class AuthenticationStrategyProviderImpl(
    private val nativeLoginStrategy: NativeAuthenticationStrategy,
    private val oAuthLoginStrategy: OAuthAuthenticationStrategy
) : AuthenticationStrategyProvider {

    override fun getStrategy(type: AuthenticationType): AuthenticationStrategy {
        return when (type) {
            AuthenticationType.NATIVE -> nativeLoginStrategy
            else -> oAuthLoginStrategy
        }
    }
}