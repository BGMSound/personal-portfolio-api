package kr.bgmsound.bgmlab.application.profile.service

import kr.bgmsound.bgmlab.application.profile.dto.ProfilePatchDto
import kr.bgmsound.bgmlab.model.Profile

interface ProfileService {

    fun getProfile(): Profile

    fun updateProfile(profile: ProfilePatchDto)

}