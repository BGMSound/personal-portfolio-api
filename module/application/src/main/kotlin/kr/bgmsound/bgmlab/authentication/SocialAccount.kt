package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.model.User

data class SocialAccount(
    override val userId: String,
    val provider: String,
    val socialId: String
) : User.Account {

    companion object {
        fun of(user: User, provider: String, socialId: String): SocialAccount {
            return SocialAccount(
                userId = user.id,
                provider = provider,
                socialId = socialId
            )
        }
    }
}