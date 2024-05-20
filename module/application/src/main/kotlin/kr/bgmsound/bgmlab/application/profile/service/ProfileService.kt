package kr.bgmsound.bgmlab.application.profile.service

import kr.bgmsound.bgmlab.application.profile.dto.PatchProfileDto
import kr.bgmsound.bgmlab.model.Profile

interface ProfileService {

    fun getProfile(): Profile

    fun updateProfile(profile: PatchProfileDto)

}