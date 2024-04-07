package kr.bgmsound.bgmlab.repository

import kr.bgmsound.bgmlab.model.User

interface UserProfileRepository {

    fun save(userId: String, userProfile: User.Profile)

    fun findByUserId(userId: String): User.Profile?

}