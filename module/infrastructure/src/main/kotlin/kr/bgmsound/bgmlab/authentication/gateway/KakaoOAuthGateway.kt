package kr.bgmsound.bgmlab.authentication.gateway

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import kr.bgmsound.bgmlab.authentication.OAuthProviderType
import kr.bgmsound.bgmlab.authentication.OAuthResult
import kr.bgmsound.bgmlab.authentication.TypedOAuthGateway
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.reactive.function.client.WebClient

@Component
class KakaoOAuthGateway(
    @Qualifier("kakaoAuthClient") private val kakaoAuthWebClient: WebClient,
    @Qualifier("kakaoLoginClient") private val kakaoLoginWebClient: WebClient,

    @Value("\${oauth2.client.registration.kakao.client-id}") private val clientId: String,
    @Value("\${oauth2.client.registration.kakao.client-secret}") private val clientSecret: String,
    @Value("\${oauth2.client.registration.kakao.redirect-uri}") private val redirectUri: String,

    private val objectMapper: ObjectMapper
) : TypedOAuthGateway {

    override fun authenticate(code: String): OAuthResult {
        val tokenResponse = requestToken(code)
        val loginResponse = loginToKakao(tokenResponse.accessToken)

        return OAuthResult(OAuthProviderType.KAKAO.toString(), loginResponse.id)
    }

    override fun getType(): OAuthProviderType {
        return OAuthProviderType.KAKAO
    }

    private fun requestToken(code: String): KakaoTokenResponse {
        val params = LinkedMultiValueMap<String, String>()
        params.add("grant_type", "authorization_code")
        params.add("client_id", clientId)
        params.add("redirect_uri", redirectUri)
        params.add("code", code)
        params.add("client_secret", clientSecret)

        val response = kakaoAuthWebClient
            .post()
            .headers {
                it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                it.acceptCharset = listOf(Charsets.UTF_8)
            }
            .bodyValue(params)
            .retrieve()
            .bodyToMono(String::class.java)
            .block()
        return objectMapper.readValue(response, KakaoTokenResponse::class.java)
    }

    private data class KakaoTokenResponse(
        @JsonProperty("access_token")
        val accessToken: String,
    )

    private fun loginToKakao(accessToken: String): KakaoLoginResponse {
        val response = kakaoLoginWebClient
            .get()
            .headers {
                it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                it.acceptCharset = listOf(Charsets.UTF_8)
                it.add("Authorization", "Bearer $accessToken")
            }
            .retrieve()
            .bodyToMono(String::class.java)
            .block()
        return objectMapper.readValue(response, KakaoLoginResponse::class.java)
    }

    private data class KakaoLoginResponse(
        @JsonProperty("id")
        val id: String,
    )
}