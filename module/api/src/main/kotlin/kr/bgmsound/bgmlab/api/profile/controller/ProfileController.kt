package kr.bgmsound.bgmlab.api.profile.controller

import kr.bgmsound.bgmlab.application.profile.service.ProfileService
import kr.bgmsound.bgmlab.model.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ProfileController(
    private val profileService: ProfileService
) {

    @GetMapping("/profile")
    fun getProfile(): ResponseEntity<Profile> {
        val profile = profileService.getProfile()

        return ResponseEntity.ok(profile)
    }
}