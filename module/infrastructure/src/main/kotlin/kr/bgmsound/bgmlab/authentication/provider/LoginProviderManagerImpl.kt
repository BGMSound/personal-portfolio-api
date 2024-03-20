package kr.bgmsound.bgmlab.authentication.provider

import kr.bgmsound.bgmlab.authentication.AuthenticationProvider
import kr.bgmsound.bgmlab.authentication.dto.LoginType
import kr.bgmsound.bgmlab.authentication.gateway.OAuthGateway
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component

@Component
class LoginProviderManagerImpl(
    private val oAuthGateways: ObjectProvider<OAuthGateway>
) : LoginProviderManager {

    override fun getLoginProvider(type: LoginType): AuthenticationProvider {
        TODO()
    }
}