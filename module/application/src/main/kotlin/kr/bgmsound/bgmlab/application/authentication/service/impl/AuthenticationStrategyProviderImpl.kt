package kr.bgmsound.bgmlab.application.authentication.service.impl

import kr.bgmsound.bgmlab.application.authentication.*
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