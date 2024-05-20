package kr.bgmsound.bgmlab.application.profile

import kr.bgmsound.bgmlab.application.profile.dto.PatchProfileDto
import kr.bgmsound.bgmlab.model.Profile

interface SingletonProfileRepository {

    fun get(): Profile

    fun update(profile: PatchProfileDto)

}