package kr.bgmsound.bgmlab.output.provider

import kr.bgmsound.bgmlab.dto.SocialLoginResultDto

interface SocialLoginProvider {
    fun login(code: String): SocialLoginResultDto
}