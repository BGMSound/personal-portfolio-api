package kr.bgmsound.bgmlab.authentication.config


import kr.bgmsound.bgmlab.authentication.dto.LoginType
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class OAuthWebClientConfig(
    @Qualifier("OAuthWebClientProperties")
    private val oAuthWebClientProperties: OAuthWebClientProperties,
) {
    private enum class OAuthType {
        AUTHORIZATION, LOGIN
    }

    @Bean("kakaoAuthClient")
    fun kakaoAuthClient(): WebClient =
        buildOAuthWebClient(LoginType.KAKAO.name.lowercase(), OAuthType.AUTHORIZATION)

    @Bean("kakaoLoginClient")
    fun kakaoLoginClient(): WebClient = buildOAuthWebClient(LoginType.KAKAO.name.lowercase(), OAuthType.LOGIN)

    private fun buildOAuthWebClient(provider: String, type: OAuthType): WebClient {
        val config = oAuthWebClientProperties.getClientConfig(provider)
        return WebClient
            .builder()
            .baseUrl(
                when (type) {
                    OAuthType.AUTHORIZATION -> config.authorizationUri
                    OAuthType.LOGIN -> config.loginUri
                }
            )
            .addHeaderIfExists(config)
            .build()
    }

    private fun WebClient.Builder.addHeaderIfExists(clientConfig: ClientConfig): WebClient.Builder {
        clientConfig.let { property ->
            property.header?.onEach { header ->
                this@addHeaderIfExists.defaultHeaders {
                    it.set(header.key, header.value)
                }
            }
        }
        return this
    }
}