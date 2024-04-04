package kr.bgmsound.bgmlab.authentication.config


import kr.bgmsound.bgmlab.authentication.OAuthProviderType
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class OAuthWebClientConfig(
    @Qualifier("OAuthWebClientProperties")
    private val oAuthWebClientProperties: OAuthWebClientProperties
) {

    @Bean("kakaoAuthClient")
    fun kakaoAuthClient(): WebClient =
        buildOAuthWebClient(OAuthProviderType.KAKAO.name.lowercase(), AccessType.AUTHENTICATION)

    @Bean("kakaoLoginClient")
    fun kakaoLoginClient(): WebClient = buildOAuthWebClient(OAuthProviderType.KAKAO.name.lowercase(), AccessType.LOGIN)

    private fun buildOAuthWebClient(provider: String, type: AccessType): WebClient {
        val config = oAuthWebClientProperties.getClientConfig(provider)
        return WebClient
            .builder()
            .baseUrl(
                when (type) {
                    AccessType.AUTHENTICATION -> config.authorizationUri
                    AccessType.LOGIN -> config.loginUri
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

    private enum class AccessType {
        AUTHENTICATION,
        LOGIN
    }
}