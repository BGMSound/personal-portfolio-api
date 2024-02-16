package kr.bgmsound.bgmlab.provider.auth

import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.output.provider.SocialLoginProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("google")
class GoogleSocialLoginProvider : SocialLoginProvider {
    override fun login(code: String): SocialLoginResultDto {
        TODO("Not yet implemented")
    }

}