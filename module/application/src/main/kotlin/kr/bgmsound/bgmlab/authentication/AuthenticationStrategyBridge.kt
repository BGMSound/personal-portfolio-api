package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.dto.LoginType

interface AuthenticationStrategyBridge {

    fun getProvider(type: LoginType): AuthenticationStrategy

}