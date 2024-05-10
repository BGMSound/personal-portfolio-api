package kr.bgmsound.bgmlab.application.authentication.service.impl

import kr.bgmsound.bgmlab.application.authentication.*
import org.springframework.stereotype.Component

@Component
class AuthenticationSupportProviderImpl(
    private val nativeLoginSupport: NativeAuthenticationSupport,
    private val oAuthLoginSupport: OAuthenticationSupport
) : AuthenticationSupportProvider {

    override fun from(type: AuthenticationType): AuthenticationSupport {
        return when (type) {
            AuthenticationType.NATIVE -> nativeLoginSupport
            AuthenticationType.OAUTH -> oAuthLoginSupport
        }
    }
}