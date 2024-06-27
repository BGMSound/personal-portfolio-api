package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.application.authentication.gateway.OAuthGateway

interface TypedOAuthGateway : OAuthGateway {

    fun getType(): OAuthProviderType

}