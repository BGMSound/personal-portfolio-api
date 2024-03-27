package kr.bgmsound.bgmlab.authentication.gateway

import kr.bgmsound.bgmlab.authentication.AuthenticationType
import kr.bgmsound.bgmlab.authentication.OAuthResult

interface OAuthGateway {

    fun authenticate(code: String): OAuthResult

    fun getType(): AuthenticationType

}