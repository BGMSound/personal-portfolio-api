package kr.bgmsound.bgmlab.application.authentication.service

import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticatedUserDto
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationRequestDto
import kr.bgmsound.bgmlab.model.Token

interface AuthenticationService {

    fun login(request: AuthenticationRequestDto): AuthenticatedUserDto

    fun refresh(refreshToken: Token): Token

}