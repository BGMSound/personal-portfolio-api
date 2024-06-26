package kr.bgmsound.bgmlab.application.authentication

import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationRequestDto
import kr.bgmsound.bgmlab.model.User

interface AuthenticationSupport {

    fun authenticate(authentication: AuthenticationRequestDto): User

}