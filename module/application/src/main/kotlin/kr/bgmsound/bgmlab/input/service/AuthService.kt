package kr.bgmsound.bgmlab.input.service

import kr.bgmsound.bgmlab.output.authentication.LoginProviderType
import kr.bgmsound.bgmlab.dto.LoggedInUserDto

interface AuthService {
    fun socialLogin(type: LoginProviderType, code: String): LoggedInUserDto
}