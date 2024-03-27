package kr.bgmsound.bgmlab.authentication.gateway

import kr.bgmsound.bgmlab.authentication.OAuthResult
import kr.bgmsound.bgmlab.authentication.AuthenticationType
import org.springframework.stereotype.Component

@Component
class GithubOAuthGateway : OAuthGateway {
    override fun authenticate(code: String): OAuthResult {
        TODO("Not yet implemented")
    }

    override fun getType(): AuthenticationType {
        return AuthenticationType.GITHUB
    }
}