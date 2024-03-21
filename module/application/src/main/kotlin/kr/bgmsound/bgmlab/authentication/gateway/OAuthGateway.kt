package kr.bgmsound.bgmlab.authentication.gateway

import kr.bgmsound.bgmlab.authentication.LoginType
import kr.bgmsound.bgmlab.authentication.dto.OAuthResult

interface OAuthGateway {

    fun authenticate(code: String): OAuthResult

    fun getType(): LoginType

}