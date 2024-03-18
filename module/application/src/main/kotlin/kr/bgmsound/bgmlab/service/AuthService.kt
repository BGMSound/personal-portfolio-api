package kr.bgmsound.bgmlab.service

import kr.bgmsound.bgmlab.dto.AuthenticationDto
import kr.bgmsound.bgmlab.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.model.Token

interface AuthService {

    fun login(authentication: AuthenticationDto): LoggedInUserDto

    fun refresh(refreshToken: Token): Token
}