package kr.bgmsound.bgmlab.controller.auth.oauth

import kr.bgmsound.bgmlab.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.dto.response.LoginResponse
import kr.bgmsound.bgmlab.authentication.service.AuthService
import kr.bgmsound.bgmlab.authentication.dto.LoginType
import kr.bgmsound.bgmlab.util.getLogger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class KakaoOAuthController(
    private val authService: AuthService,
) {

    val log = getLogger()

    @GetMapping("/oauth/kakao")
    fun login(@RequestParam code: String): LoginResponse {
        val loggedInUser = authService.login(authentication(credentials = code))
        log.info("${loggedInUser.name} has logged in.")

        return LoginResponse.from(loggedInUser)
    }

    private fun authentication(credentials: String): AuthenticationDto {
        return AuthenticationDto(
            type = LoginType.KAKAO,
            principal = "",
            credentials = credentials
        )
    }
}