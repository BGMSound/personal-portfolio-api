package kr.bgmsound.bgmlab.application.authentication

import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.model.User

interface AuthenticationStrategy {

    fun authenticate(authentication: AuthenticationDto): User

}