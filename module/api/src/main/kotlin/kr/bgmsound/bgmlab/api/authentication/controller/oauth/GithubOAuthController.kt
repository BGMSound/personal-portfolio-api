package kr.bgmsound.bgmlab.api.authentication.controller.oauth

import kr.bgmsound.bgmlab.api.authentication.OAuthType
import kr.bgmsound.bgmlab.api.authentication.dto.response.LoginResponse
import kr.bgmsound.bgmlab.application.authentication.AuthenticationType
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationRequestDto
import kr.bgmsound.bgmlab.application.authentication.service.AuthenticationService
import kr.bgmsound.bgmlab.application.getLogger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GithubOAuthController(
    private val authenticationService: AuthenticationService
) {
    val log = getLogger<GithubOAuthController>()

    @GetMapping("/oauth/github")
    fun login(@RequestParam code: String): LoginResponse {
        val loggedInUser = authenticationService.login(authenticationRequest(credentials = code))
        log.info("${loggedInUser.name} (@${loggedInUser.displayId}) has logged in.")

        return LoginResponse.from(loggedInUser)
    }

    private fun authenticationRequest(credentials: String): AuthenticationRequestDto {
        return AuthenticationRequestDto(
            type = AuthenticationType.OAUTH,
            principal = OAuthType.GITHUB,
            credentials = credentials
        )
    }
}