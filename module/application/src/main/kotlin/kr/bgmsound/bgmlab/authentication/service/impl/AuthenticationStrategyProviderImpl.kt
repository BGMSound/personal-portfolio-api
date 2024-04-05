package kr.bgmsound.bgmlab.authentication.service.impl

import kr.bgmsound.bgmlab.authentication.*
import org.springframework.stereotype.Component

@Component
class AuthenticationStrategyProviderImpl(
    private val nativeLoginStrategy: NativeAuthenticationStrategy,
    private val oAuthLoginStrategy: OAuthAuthenticationStrategy
) : AuthenticationStrategyProvider {

    override fun getStrategy(type: AuthenticationType): AuthenticationStrategy {
        return when (type) {
            AuthenticationType.NATIVE -> nativeLoginStrategy
            AuthenticationType.OAUTH -> oAuthLoginStrategy
        }
    }
}