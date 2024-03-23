package kr.bgmsound.bgmlab.controller.auth

import kr.bgmsound.bgmlab.authentication.service.AuthService
import kr.bgmsound.bgmlab.dto.request.RefreshRequest
import kr.bgmsound.bgmlab.dto.response.RefreshResponse
import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.model.TokenType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService,
) {

    @GetMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequest): RefreshResponse {
        val token = authService.refresh(refreshToken = Token(TokenType.REFRESH, request.refreshToken))
        return RefreshResponse(token.provider)
    }

}