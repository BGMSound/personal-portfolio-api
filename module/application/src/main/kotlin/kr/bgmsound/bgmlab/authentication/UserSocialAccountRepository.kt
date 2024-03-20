package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.dto.LoginType
import kr.bgmsound.bgmlab.authentication.dto.SocialAccount

interface UserSocialAccountRepository {

    fun findBySocialId(provider: LoginType, socialId: String): SocialAccount?

    fun save(account: SocialAccount)

}