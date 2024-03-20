package kr.bgmsound.bgmlab.authentication.repository

import kr.bgmsound.bgmlab.authentication.dto.SocialAccount
import kr.bgmsound.bgmlab.authentication.dto.LoginType

interface UserSocialAccountRepository {

    fun findBySocialId(provider: LoginType, socialId: String): SocialAccount?

    fun save(account: SocialAccount)

}