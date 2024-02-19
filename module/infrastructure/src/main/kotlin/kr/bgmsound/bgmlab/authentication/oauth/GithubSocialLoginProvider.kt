package kr.bgmsound.bgmlab.authentication.oauth

import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.output.authentication.LoginProviderType
import kr.bgmsound.bgmlab.output.authentication.SocialLoginProvider
import org.springframework.stereotype.Component

@Component
class GithubSocialLoginProvider : SocialLoginProvider {
    override fun login(code: String): SocialLoginResultDto {
        TODO("Not yet implemented")
    }

    override fun getType(): LoginProviderType {
        return LoginProviderType.GITHUB
    }
}