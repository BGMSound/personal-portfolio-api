package kr.bgmsound.bgmlab.authentication.gateway

import kr.bgmsound.bgmlab.authentication.OAuthProviderType
import kr.bgmsound.bgmlab.authentication.OAuthResult
import kr.bgmsound.bgmlab.authentication.TypedOAuthGateway
import org.springframework.stereotype.Component

@Component
class GithubOAuthGateway : TypedOAuthGateway {
    override fun authenticate(code: String): OAuthResult {
        TODO("Not yet implemented")
    }

    override fun getType(): OAuthProviderType {
        return OAuthProviderType.GITHUB
    }
}