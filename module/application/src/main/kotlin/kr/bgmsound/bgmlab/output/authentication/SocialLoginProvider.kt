package kr.bgmsound.bgmlab.output.authentication

import kr.bgmsound.bgmlab.dto.SocialLoginResultDto

interface SocialLoginProvider {
    fun login(code: String): SocialLoginResultDto

    fun getType(): LoginProviderType
}