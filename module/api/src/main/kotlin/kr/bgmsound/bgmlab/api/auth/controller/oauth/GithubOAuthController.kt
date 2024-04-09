package kr.bgmsound.bgmlab.api.auth.controller.oauth

import kr.bgmsound.bgmlab.api.auth.OAuthType
import kr.bgmsound.bgmlab.api.auth.dto.response.LoginResponse
import kr.bgmsound.bgmlab.application.authentication.AuthenticationType
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.application.authentication.service.AuthenticationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GithubOAuthController(
    private val authenticationService: AuthenticationService
) {

    @GetMapping("/oauth/github")
    fun login(@RequestParam code: String) : LoginResponse {
        val loggedInUser = authenticationService.login(authentication(credentials = code))
        return LoginResponse.from(loggedInUser)
    }

    private fun authentication(credentials: String): AuthenticationDto {
        return AuthenticationDto(
            type = AuthenticationType.OAUTH,
            principal = OAuthType.GITHUB,
            credentials = credentials
        )
    }

}