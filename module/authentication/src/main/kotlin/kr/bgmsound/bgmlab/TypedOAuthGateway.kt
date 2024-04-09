package kr.bgmsound.bgmlab

import kr.bgmsound.bgmlab.application.authentication.gateway.OAuthGateway

interface TypedOAuthGateway : OAuthGateway {

    fun getType(): OAuthProviderType

}