package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.dto.LoginType
import kr.bgmsound.bgmlab.authentication.gateway.OAuthGateway
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component

@Component
class OAuthGatewayFactoryImpl(
    private val oAuthGateways: ObjectProvider<OAuthGateway>
) : OAuthGatewayFactory {

    override fun of(type: LoginType): OAuthGateway {
        return oAuthGateways.find { it.getType() == type } ?: throw IllegalArgumentException("Not found OAuthGateway for $type")
    }
}