package kr.bgmsound.bgmlab.authentication.service.impl

import kr.bgmsound.bgmlab.authentication.AuthenticationProvider
import kr.bgmsound.bgmlab.authentication.AuthenticationProviderManager
import kr.bgmsound.bgmlab.authentication.dto.LoginType
import org.springframework.stereotype.Component

@Component
class AuthenticationProviderManagerImpl(
    private val nativeLoginProvider: NativeAuthenticationProvider,
    private val oAuthLoginProvider: OAuthAuthenticationProvider
) : AuthenticationProviderManager {

    override fun getProvider(type: LoginType): AuthenticationProvider {
        return when(type) {
            LoginType.NATIVE -> nativeLoginProvider
            else -> oAuthLoginProvider
        }
    }
}