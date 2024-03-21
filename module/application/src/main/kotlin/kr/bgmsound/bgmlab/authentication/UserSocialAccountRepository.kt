package kr.bgmsound.bgmlab.authentication

interface UserSocialAccountRepository {

    fun findBySocialId(provider: LoginType, socialId: String): SocialAccount?

    fun save(account: SocialAccount)

}