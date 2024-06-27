package kr.bgmsound.bgmlab

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class OAuthWebClientUtil(
    _oAuthWebClientProperties: OAuthWebClientProperties
) {
    
    init {
        oAuthWebClientProperties = _oAuthWebClientProperties
    }
    
    companion object {
        private lateinit var oAuthWebClientProperties: OAuthWebClientProperties
        
        fun WebClient.RequestBodyUriSpec.oauthType(
            type: OAuthProviderType
        ): WebClient.RequestBodySpec {
            val config = oAuthWebClientProperties.getClientConfig(type.name.lowercase())
            headers {
                it.acceptCharset = listOf(Charsets.UTF_8)
            }
            uri(config.authorizationUri)
            addHeaderIfExistsByConfig(config)
            return this
        }

        fun WebClient.RequestHeadersUriSpec<*>.oauthType(
            type: OAuthProviderType
        ): WebClient.RequestHeadersSpec<*>{
            val config = oAuthWebClientProperties.getClientConfig(type.name.lowercase())
            headers {
                it.acceptCharset = listOf(Charsets.UTF_8)
            }
            addHeaderIfExistsByConfig(config)
            uri(config.loginUri)
            return this
        }

        private fun WebClient.RequestBodySpec.addHeaderIfExistsByConfig(
            config: ClientConfig
        ): WebClient.RequestBodySpec {
            config.let { property ->
                property.header?.onEach { header ->
                    this@addHeaderIfExistsByConfig.headers {
                        it.set(header.key, header.value)
                    }
                }
            }
            return this
        }

        private fun WebClient.RequestHeadersSpec<*>.addHeaderIfExistsByConfig(
            config: ClientConfig
        ): WebClient.RequestHeadersSpec<*> {
            config.let { property ->
                property.header?.onEach { header ->
                    this@addHeaderIfExistsByConfig.headers {
                        it.set(header.key, header.value)
                    }
                }
            }
            return this
        }
    }
}