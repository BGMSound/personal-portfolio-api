package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.dto.LoginType
import org.springframework.stereotype.Component

@Component
class AuthenticationStrategyBridgeImpl(
    private val nativeLoginProvider: NativeAuthenticationStrategy,
    private val oAuthLoginProvider: OAuthAuthenticationStrategy
) : AuthenticationStrategyBridge {

    override fun getProvider(type: LoginType): AuthenticationStrategy {
        return when(type) {
            LoginType.NATIVE -> nativeLoginProvider
            else -> oAuthLoginProvider
        }
    }
}