package kr.bgmsound.bgmlab.application.profile

import kr.bgmsound.bgmlab.application.profile.dto.ProfilePatchDto
import kr.bgmsound.bgmlab.model.Profile

interface SingletonProfileRepository {

    fun get(): Profile

    fun update(profile: ProfilePatchDto)

}