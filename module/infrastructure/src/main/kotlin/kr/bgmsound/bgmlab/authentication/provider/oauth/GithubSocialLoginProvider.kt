package kr.bgmsound.bgmlab.authentication.provider.oauth

import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.strategy.LoginProviderType
import kr.bgmsound.bgmlab.gateway.input.authentication.SocialLoginProvider
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