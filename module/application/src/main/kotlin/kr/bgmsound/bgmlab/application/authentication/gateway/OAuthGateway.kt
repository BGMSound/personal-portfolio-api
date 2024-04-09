package kr.bgmsound.bgmlab.application.authentication.gateway

import kr.bgmsound.bgmlab.application.authentication.OAuthResult

interface OAuthGateway {

    fun authenticate(code: String): OAuthResult

}