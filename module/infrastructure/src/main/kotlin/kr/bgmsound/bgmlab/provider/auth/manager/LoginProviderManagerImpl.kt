package kr.bgmsound.bgmlab.provider.auth.manager

import kr.bgmsound.bgmlab.LoginProviderType
import kr.bgmsound.bgmlab.output.provider.SocialLoginProvider
import kr.bgmsound.bgmlab.output.provider.LoginProviderManager
import kr.bgmsound.bgmlab.provider.auth.GithubSocialLoginProvider
import kr.bgmsound.bgmlab.provider.auth.GoogleSocialLoginProvider
import kr.bgmsound.bgmlab.provider.auth.KakaoSocialLoginProvider
import org.springframework.stereotype.Component

@Component
class LoginProviderManagerImpl(
    private val kakaoAuthenticationProvider: KakaoSocialLoginProvider,
    private val googleAuthenticationProvider: GoogleSocialLoginProvider,
    private val githubAuthenticationProvider: GithubSocialLoginProvider
) : LoginProviderManager {

    override fun getSocialLoginProvider(provider: LoginProviderType): SocialLoginProvider {
        return when (provider) {
            LoginProviderType.KAKAO -> kakaoAuthenticationProvider
            LoginProviderType.GOOGLE -> googleAuthenticationProvider
            LoginProviderType.GITHUB -> githubAuthenticationProvider
            else -> throw IllegalArgumentException("Not supported AuthProviderType")
        }
    }
}