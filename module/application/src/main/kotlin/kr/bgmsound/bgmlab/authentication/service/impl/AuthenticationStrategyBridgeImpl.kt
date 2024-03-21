package kr.bgmsound.bgmlab.authentication.service.impl

import kr.bgmsound.bgmlab.authentication.AuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.AuthenticationStrategyBridge
import kr.bgmsound.bgmlab.authentication.NativeAuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.OAuthAuthenticationStrategy
import kr.bgmsound.bgmlab.authentication.LoginType
import org.springframework.stereotype.Component

@Component
class AuthenticationStrategyBridgeImpl(
    private val nativeLoginProvider: NativeAuthenticationStrategy,
    private val oAuthLoginProvider: OAuthAuthenticationStrategy
) : AuthenticationStrategyBridge {

    override fun getProvider(type: LoginType): AuthenticationStrategy {
        return when (type) {
            LoginType.NATIVE -> nativeLoginProvider
            else -> oAuthLoginProvider
        }
    }
}