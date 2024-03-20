package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.dto.LoginType

interface AuthenticationProviderManager {

    fun getProvider(type: LoginType): AuthenticationProvider

}