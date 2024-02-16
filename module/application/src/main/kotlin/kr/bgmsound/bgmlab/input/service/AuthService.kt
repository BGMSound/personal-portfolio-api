package kr.bgmsound.bgmlab.input.service

import kr.bgmsound.bgmlab.LoginProviderType
import kr.bgmsound.bgmlab.dto.SocialUserDto
import kr.bgmsound.bgmlab.dto.Token

interface AuthService {
    fun socialLogin(type: LoginProviderType, code: String): Pair<SocialUserDto, Token>
}