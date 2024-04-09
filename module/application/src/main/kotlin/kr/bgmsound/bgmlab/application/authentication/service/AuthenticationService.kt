package kr.bgmsound.bgmlab.application.authentication.service

import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticatedUserDto
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.model.Token

interface AuthenticationService {

    fun login(authentication: AuthenticationDto): AuthenticatedUserDto

    fun refresh(refreshToken: Token): Token

}