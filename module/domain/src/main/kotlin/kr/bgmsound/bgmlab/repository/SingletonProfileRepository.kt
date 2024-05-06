package kr.bgmsound.bgmlab.repository

import kr.bgmsound.bgmlab.model.Profile

interface SingletonProfileRepository {

    fun getProfile(): Profile

    fun updateProfile(profile: Profile)

}