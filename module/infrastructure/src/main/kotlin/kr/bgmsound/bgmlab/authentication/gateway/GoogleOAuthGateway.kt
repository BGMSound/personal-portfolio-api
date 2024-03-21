package kr.bgmsound.bgmlab.authentication.gateway

import kr.bgmsound.bgmlab.authentication.dto.OAuthResult
import kr.bgmsound.bgmlab.authentication.LoginType
import org.springframework.stereotype.Component

@Component
class GoogleOAuthGateway : OAuthGateway {
    override fun authenticate(code: String): OAuthResult {
        TODO("Not yet implemented")
    }

    override fun getType(): LoginType {
        return LoginType.GOOGLE
    }
}