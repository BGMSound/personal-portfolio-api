package kr.bgmsound.bgmlab.gateway.output.authentication

import kr.bgmsound.bgmlab.strategy.LoginProviderType
import kr.bgmsound.bgmlab.dto.SocialLoginResultDto

interface SocialLoginProvider {
    fun login(code: String): SocialLoginResultDto

    fun getType(): LoginProviderType
}