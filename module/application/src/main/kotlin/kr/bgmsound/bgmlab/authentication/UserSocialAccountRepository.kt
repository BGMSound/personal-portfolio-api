package kr.bgmsound.bgmlab.authentication

interface UserSocialAccountRepository {

    fun findBySocialId(provider: AuthenticationType, socialId: String): SocialAccount?

    fun save(account: SocialAccount)

}