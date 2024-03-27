package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.model.User

data class SocialAccount(
    override val userId: String,
    val provider: AuthenticationType,
    val socialId: String
) : User.Account {

    companion object {
        fun of(user: User, provider: AuthenticationType, socialId: String): SocialAccount {
            return SocialAccount(
                userId = user.id,
                provider = provider,
                socialId = socialId
            )
        }
    }
}