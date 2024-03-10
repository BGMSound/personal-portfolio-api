package kr.bgmsound.bgmlab.service

import kr.bgmsound.bgmlab.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.strategy.LoginProviderType

interface AuthService {
    fun socialLogin(type: LoginProviderType, code: String): LoggedInUserDto
}