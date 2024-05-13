package kr.bgmsound.bgmlab.repository

import kr.bgmsound.bgmlab.model.Profile

interface SingletonProfileRepository {

    fun get(): Profile

    fun update(profile: Profile)

}