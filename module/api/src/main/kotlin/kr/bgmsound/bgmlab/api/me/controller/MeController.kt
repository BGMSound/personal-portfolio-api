package kr.bgmsound.bgmlab.api.me.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/me")
class MeController {

    @RequestMapping("/info")
    fun info(@AuthenticationPrincipal userId: String): String {
        return userId
    }

}