package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.gateway.OAuthGateway

interface TypedOAuthGateway : OAuthGateway {

    fun getType(): OAuthProviderType

}