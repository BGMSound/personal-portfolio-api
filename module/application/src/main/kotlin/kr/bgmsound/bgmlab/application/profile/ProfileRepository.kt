package kr.bgmsound.bgmlab.application.profile

import kr.bgmsound.bgmlab.application.profile.dto.ProfilePatchDto
import kr.bgmsound.bgmlab.model.Profile

interface ProfileRepository {

    fun get(): Profile

    fun update(profile: ProfilePatchDto)

}