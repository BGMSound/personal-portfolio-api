package kr.bgmsound.bgmlab.api.profile.controller

import kr.bgmsound.bgmlab.api.profile.dto.response.ProfileResponse
import kr.bgmsound.bgmlab.application.profile.service.ProfileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ProfileController(
    private val profileService: ProfileService
) {

    @GetMapping("/profile")
    fun getProfile(): ProfileResponse {
        val profile = profileService.getProfile()

        return ProfileResponse.of(profile)
    }
}