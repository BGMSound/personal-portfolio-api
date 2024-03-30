package kr.bgmsound.bgmlab.authentication

interface UserSocialAccountRepository {

    fun findBySocialId(provider: String, socialId: String): SocialAccount?

    fun save(account: SocialAccount)

}