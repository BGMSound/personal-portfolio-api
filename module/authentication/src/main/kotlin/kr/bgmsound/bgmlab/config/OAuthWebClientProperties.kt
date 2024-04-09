package kr.bgmsound.bgmlab.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("oauth2.client")
class OAuthWebClientProperties {
    lateinit var provider: Map<String, ClientConfig>

    fun getClientConfig(provider: String): ClientConfig {
        return this.provider[provider] ?: throw IllegalArgumentException("Not found provider: $provider")
    }
}

data class ClientConfig(
    val authorizationUri: String,
    val loginUri: String,
    val header: Map<String, String>? = null,
)