package kr.bgmsound.bgmlab.repository

import kr.bgmsound.bgmlab.model.User

interface UserSocialAccountRepository {

    fun findBySocialId(provider: String, socialId: String): User?

    fun save(userId: String, account: User.Account)
}