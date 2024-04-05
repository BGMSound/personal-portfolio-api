package kr.bgmsound.bgmlab.authentication.service

import kr.bgmsound.bgmlab.authentication.dto.AuthenticatedUserDto
import kr.bgmsound.bgmlab.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.model.Token

interface AuthenticationService {

    fun login(authentication: AuthenticationDto): AuthenticatedUserDto

    fun refresh(refreshToken: Token): Token

}