package kr.bgmsound.bgmlab
import kr.bgmsound.bgmlab.application.authentication.OAuthGatewayFactory
import kr.bgmsound.bgmlab.application.authentication.gateway.OAuthGateway
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component

@Component
class OAuthGatewayFactoryImpl(
    private val oAuthGateways: ObjectProvider<TypedOAuthGateway>
) : OAuthGatewayFactory {
    override fun of(provider: String): OAuthGateway {
        val oauthProvider = OAuthProviderType.from(provider = provider)
        return findOAuthGateway(provider = oauthProvider)
            ?: throw IllegalArgumentException("Not found OAuthGateway for $provider")
    }

    private fun findOAuthGateway(provider: OAuthProviderType): TypedOAuthGateway? {
        return oAuthGateways.find { it.getType() == provider }
    }
}