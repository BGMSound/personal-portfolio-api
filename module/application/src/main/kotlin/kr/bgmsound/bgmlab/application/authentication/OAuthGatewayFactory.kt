package kr.bgmsound.bgmlab.application.authentication

import kr.bgmsound.bgmlab.application.authentication.gateway.OAuthGateway

interface OAuthGatewayFactory {

    fun of(provider: String): OAuthGateway

}