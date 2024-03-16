package kr.bgmsound.bgmlab.adapter.authentication.account.repository

import kr.bgmsound.bgmlab.dto.account.SocialAccount
import kr.bgmsound.bgmlab.strategy.LoginProviderType

interface UserSocialAccountRepository {

    fun findBySocialId(provider: LoginProviderType, socialId: String): SocialAccount?

    fun save(account: SocialAccount)

}