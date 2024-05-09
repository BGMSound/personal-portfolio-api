package kr.bgmsound.bgmlab.application.authentication.service.impl

import kr.bgmsound.bgmlab.application.authentication.*
import org.springframework.stereotype.Component

@Component
class AuthenticationSupportProviderImpl(
    private val nativeLoginStrategy: NativeAuthenticationSupport,
    private val oAuthLoginStrategy: OAuthAuthenticationSupport
) : AuthenticationSupportProvider {

    override fun from(type: AuthenticationType): AuthenticationSupport {
        return when (type) {
            AuthenticationType.NATIVE -> nativeLoginStrategy
            AuthenticationType.OAUTH -> oAuthLoginStrategy
        }
    }
}