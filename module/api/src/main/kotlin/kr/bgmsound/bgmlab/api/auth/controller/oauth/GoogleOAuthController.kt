package kr.bgmsound.bgmlab.api.auth.controller.oauth

import kr.bgmsound.bgmlab.api.auth.OAuthType
import kr.bgmsound.bgmlab.api.auth.dto.response.LoginResponse
import kr.bgmsound.bgmlab.application.authentication.AuthenticationType
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationRequestDto
import kr.bgmsound.bgmlab.application.authentication.service.AuthenticationService
import kr.bgmsound.bgmlab.application.getLogger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GoogleOAuthController(
    private val authService: AuthenticationService
) {
    val log = getLogger<GoogleOAuthController>()

    @GetMapping("/oauth/google")
    fun login(@RequestParam code: String): LoginResponse {
        val loggedInUser = authService.login(makeAuthenticationRequest(credentials = code))
        log.info("${loggedInUser.name} (@${loggedInUser.displayId}) has logged in.")

        return LoginResponse.from(loggedInUser)
    }

    private fun makeAuthenticationRequest(credentials: String): AuthenticationRequestDto {
        return AuthenticationRequestDto(
            type = AuthenticationType.OAUTH,
            principal = OAuthType.GOOGLE,
            credentials = credentials
        )
    }
}