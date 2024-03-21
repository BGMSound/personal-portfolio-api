package kr.bgmsound.bgmlab.authentication.service

import kr.bgmsound.bgmlab.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.authentication.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.model.Token

interface AuthService {

    fun login(authentication: AuthenticationDto): LoggedInUserDto

    fun refresh(refreshToken: Token): Token

}