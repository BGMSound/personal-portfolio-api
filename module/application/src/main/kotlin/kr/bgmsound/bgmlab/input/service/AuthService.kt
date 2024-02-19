package kr.bgmsound.bgmlab.input.service

import kr.bgmsound.bgmlab.output.provider.LoginProviderType
import kr.bgmsound.bgmlab.dto.SocialUserDto
import kr.bgmsound.bgmlab.dto.TokenDto

interface AuthService {
    fun socialLogin(type: LoginProviderType, code: String): Pair<SocialUserDto, TokenDto>
}