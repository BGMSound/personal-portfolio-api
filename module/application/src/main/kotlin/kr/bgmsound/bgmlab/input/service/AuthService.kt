package kr.bgmsound.bgmlab.input.service

import kr.bgmsound.bgmlab.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.output.authentication.LoginProviderType

interface AuthService {
    fun socialLogin(type: LoginProviderType, code: String): LoggedInUserDto
}