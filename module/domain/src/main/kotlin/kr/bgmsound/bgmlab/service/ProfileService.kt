package kr.bgmsound.bgmlab.service

import kr.bgmsound.bgmlab.model.Profile

interface ProfileService {

    fun getProfile(): Profile

    fun updateProfile(profile: Profile)

}