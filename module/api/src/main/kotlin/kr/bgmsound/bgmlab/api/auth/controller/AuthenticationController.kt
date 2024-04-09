package kr.bgmsound.bgmlab.api.auth.controller

import kr.bgmsound.bgmlab.api.auth.dto.request.RefreshRequest
import kr.bgmsound.bgmlab.api.auth.dto.response.RefreshResponse
import kr.bgmsound.bgmlab.application.authentication.service.AuthenticationService
import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.model.TokenType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @GetMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequest): RefreshResponse {
        val token = authenticationService.refresh(refreshToken = Token(TokenType.REFRESH, request.refreshToken))
        return RefreshResponse(accessToken = token.provider)
    }

}