package kr.bgmsound.bgmlab.gateway

import kr.bgmsound.bgmlab.OAuthProviderType
import kr.bgmsound.bgmlab.TypedOAuthGateway
import kr.bgmsound.bgmlab.application.authentication.OAuthResult
import org.springframework.stereotype.Component

@Component
class GoogleOAuthGateway : TypedOAuthGateway {
    override fun authenticate(code: String): OAuthResult {
        TODO("Not yet implemented")
    }

    override fun getType(): OAuthProviderType {
        return OAuthProviderType.GOOGLE
    }
}