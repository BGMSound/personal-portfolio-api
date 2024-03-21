package kr.bgmsound.bgmlab.authentication.service.impl

import kr.bgmsound.bgmlab.authentication.AuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.AuthenticationStrategyManager
import kr.bgmsound.bgmlab.authentication.NativeAuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.OAuthAuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.LoginType
import org.springframework.stereotype.Component

@Component
class AuthenticationStrategyManagerImpl(
    private val nativeLoginProvider: NativeAuthenticationStrategy,
    private val oAuthLoginProvider: OAuthAuthenticationStrategy
) : AuthenticationStrategyManager {

    override fun getStrategy(type: LoginType): AuthenticationStrategy {
        return when (type) {
            LoginType.NATIVE -> nativeLoginProvider
            else -> oAuthLoginProvider
        }
    }
}